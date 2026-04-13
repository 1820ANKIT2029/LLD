package parkinglot;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import parkinglot.entities.*;
import parkinglot.strategy.*;
import parkinglot.enums.VehicleSize;

public class ParkingLotSystem {
    private static volatile ParkingLotSystem instance;
    private static final Object lock = new Object();

    private List<ParkingFloor> floors;
    private Map<String, ParkingTicket> activeTickets;

    private ParkingStrategy parkingStrategy;
    private FeeStrategy feeStrategy;

    public ParkingLotSystem() {
        this.floors = new ArrayList<>();
        this.activeTickets = new HashMap<>();
        this.feeStrategy = new FlatRateFeeStrategy();
        this.parkingStrategy = new NearestFirstParkingStrategy();
    }

    public static ParkingLotSystem getInstance() {
        if(instance == null) {
            synchronized (lock)  {
                if(instance == null){
                    instance = new ParkingLotSystem();
                }
            }
        }

        return instance;
    }

    public void setParkingStrategy(ParkingStrategy parkingStrategy) {
        this.parkingStrategy = parkingStrategy;
    }

    public void setFeeStrategy(FeeStrategy feeStrategy) {
        this.feeStrategy = feeStrategy;
    }

    public void addFloor(ParkingFloor parkingFloor) {
        this.floors.add(parkingFloor);
    }

    public synchronized Optional<ParkingTicket> parkVehicle(Vehicle vehicle) {
        Optional<ParkingSpot> spotOpt = parkingStrategy.findSpot(this.floors, vehicle);

        if (spotOpt.isPresent()) {
            ParkingSpot spot = spotOpt.get();

            spot.parkVehicle(vehicle);

            ParkingTicket ticket = new ParkingTicket(vehicle, spot);
            activeTickets.put(vehicle.getLicenseNumber(), ticket);

            System.out.println("Vehicle " + vehicle.getLicenseNumber()
                    + " parked at spot " + spot.getSpotId());

            return Optional.of(ticket);
        } else {
            System.out.println("No available spot for vehicle " + vehicle.getLicenseNumber());
            return Optional.empty();
        }
    }

    public synchronized Optional<Double> unparkVehicle(String licenseNumber) {
        ParkingTicket ticket = activeTickets.remove(licenseNumber);

        if (ticket == null) {
            System.out.println("Ticket not found for vehicle " + licenseNumber);
            return Optional.empty();
        }

        ticket.getSpot().unparkVehicle();
        ticket.setExitTimestamp();

        double fee = feeStrategy.calculateFee(ticket);

        System.out.println("Vehicle " + licenseNumber
                + " unparked from spot "
                + ticket.getSpot().getSpotId());

        return Optional.of(fee);
    }
}