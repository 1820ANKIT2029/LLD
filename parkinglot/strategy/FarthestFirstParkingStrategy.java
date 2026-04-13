package parkinglot.strategy;

import java.util.List;
import java.util.Optional;
import java.util.ListIterator;
import parkinglot.entities.ParkingSpot;
import parkinglot.entities.ParkingFloor;
import parkinglot.entities.Vehicle;

public class FarthestFirstParkingStrategy implements ParkingStrategy {
    public Optional<ParkingSpot> findSpot(
        List<ParkingFloor> floors, Vehicle vehicle
    ){
        ListIterator<ParkingFloor> iterator = floors.listIterator(floors.size());

        while (iterator.hasPrevious()) {
            ParkingFloor floor = iterator.previous();

            Optional<ParkingSpot> spot = floor.findAvailableSpot(vehicle);

            if (spot.isPresent()) {
                return spot;
            }
        }

        return Optional.empty();
    }
}