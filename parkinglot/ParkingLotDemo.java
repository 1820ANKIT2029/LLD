package parkinglot;

import parkinglot.entities.*;
import parkinglot.strategy.*;
import parkinglot.enums.VehicleSize;

import java.util.Optional;

public class ParkingLotDemo {

    public static void main(String[] args) {

        ParkingLotSystem parkingLot = ParkingLotSystem.getInstance();

        // 1. Initialize floors and spots
        ParkingFloor floor1 = new ParkingFloor(1);
        floor1.addSpot(new ParkingSpot("F1-S1", VehicleSize.SMALL));
        floor1.addSpot(new ParkingSpot("F1-M1", VehicleSize.MEDIUM));
        floor1.addSpot(new ParkingSpot("F1-L1", VehicleSize.LARGE));

        ParkingFloor floor2 = new ParkingFloor(2);
        floor2.addSpot(new ParkingSpot("F2-M1", VehicleSize.MEDIUM));
        floor2.addSpot(new ParkingSpot("F2-M2", VehicleSize.MEDIUM));

        parkingLot.addFloor(floor1);
        parkingLot.addFloor(floor2);

        parkingLot.setFeeStrategy(new VehicleBasedFeeStrategy());

        // 2. Simulate vehicle entries
        System.out.println("\n--- Vehicle Entries ---");
        floor1.displayAvailability();
        floor2.displayAvailability();

        Vehicle bike = new Bike("B-123");
        Vehicle car = new Car("C-456");
        Vehicle truck = new Truck("T-789");

        Optional<ParkingTicket> bikeTicket = parkingLot.parkVehicle(bike);
        Optional<ParkingTicket> carTicket = parkingLot.parkVehicle(car);
        Optional<ParkingTicket> truckTicket = parkingLot.parkVehicle(truck);

        System.out.println("\n--- Availability after parking ---");
        floor1.displayAvailability();
        floor2.displayAvailability();

        // 3. Another car entry
        Vehicle car2 = new Car("C-999");
        Optional<ParkingTicket> car2Ticket = parkingLot.parkVehicle(car2);

        // 4. Entry that may fail
        Vehicle bike2 = new Bike("B-000");
        Optional<ParkingTicket> failedBikeTicket = parkingLot.parkVehicle(bike2);

        if (failedBikeTicket.isEmpty()) {
            System.out.println("No spot available for Bike B-000");
        }

        // 5. Vehicle exit and fee calculation
        System.out.println("\n--- Vehicle Exits ---");

        if (carTicket.isPresent()) {
            Optional<Double> feeOpt = parkingLot.unparkVehicle(car.getLicenseNumber());

            feeOpt.ifPresent(fee ->
                System.out.println("Car C-456 unparked. Fee: $" + String.format("%.2f", fee))
            );
        }

        System.out.println("\n--- Availability after one car leaves ---");
        floor1.displayAvailability();
        floor2.displayAvailability();
    }
}