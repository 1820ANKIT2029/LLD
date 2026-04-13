package parkinglot.strategy;

import java.util.List;
import java.util.Optional;
import parkinglot.entities.ParkingSpot;
import parkinglot.entities.ParkingFloor;
import parkinglot.entities.Vehicle;

public class BestFitParkingStrategy implements ParkingStrategy {
    public Optional<ParkingSpot> findSpot(
        List<ParkingFloor> floors, Vehicle vehicle
    ){
        ParkingSpot bestSpot = null;

        for (ParkingFloor floor : floors) {
            Optional<ParkingSpot> spotOpt = floor.findAvailableSpot(vehicle);

            if (spotOpt.isPresent()) {
                ParkingSpot spot = spotOpt.get();

                if (bestSpot == null) {
                    bestSpot = spot;
                } else {
                    // Smaller size = better fit
                    if (spot.getSpotSize().ordinal() < bestSpot.getSpotSize().ordinal()) {
                        bestSpot = spot;
                    }
                }
            }
        }

        if(bestSpot == null) return Optional.empty();
        return Optional.of(bestSpot);
    }
}