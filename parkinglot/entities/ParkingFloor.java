package parkinglot.entities;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import java.util.Comparator;
import parkinglot.enums.VehicleSize;

public class ParkingFloor {
    private Integer floorNumber;
    private Map<String, ParkingSpot> spots;

    public ParkingFloor(Integer floorNumber) {
        this.floorNumber = floorNumber;
        this.spots = new HashMap<>();
    }

    public synchronized void addSpot(ParkingSpot spot) {
        this.spots.put(spot.getSpotId(), spot);
    }

    public synchronized Optional<ParkingSpot> findAvailableSpot(Vehicle vehicle) {
        List<ParkingSpot> availableSpot = new ArrayList<>();
        for(ParkingSpot spot: this.spots.values()) {
            if(!spot.isOccupied() && spot.canFitVehicle(vehicle)) {
                availableSpot.add(spot);
            }
        }

        if(availableSpot.isEmpty()) {
            return Optional.empty();
        }

        availableSpot.sort(Comparator.comparing(spot -> spot.getSpotSize().getValue()));
        return Optional.of(availableSpot.get(0));
    }

    public void displayAvailability() {
        System.out.println("--- Floor " + floorNumber + " Availability ---");

        Map<VehicleSize, Integer> availableCounts = new HashMap<>();

        for (VehicleSize size : VehicleSize.values()) {
            availableCounts.put(size, 0);
        }

        for (ParkingSpot spot : spots.values()) {
            if (!spot.isOccupied()) {
                VehicleSize size = spot.getSpotSize();
                availableCounts.put(size, availableCounts.get(size) + 1);
            }
        }

        for (VehicleSize size : VehicleSize.values()) {
            System.out.println("  " + size + " spots: " + availableCounts.get(size));
        }
    }
}