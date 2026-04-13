package parkinglot.strategy;

import java.util.List;
import java.util.Optional;
import parkinglot.entities.ParkingSpot;
import parkinglot.entities.ParkingFloor;
import parkinglot.entities.Vehicle;

public class NearestFirstParkingStrategy implements ParkingStrategy {
    public Optional<ParkingSpot> findSpot(
        List<ParkingFloor> floors, Vehicle vehicle
    ){
        for(ParkingFloor floor: floors) {
            Optional<ParkingSpot> spot = floor.findAvailableSpot(vehicle);
            if(spot.isPresent()) {
                return spot;
            }
        }

        return Optional.empty();
    }
}