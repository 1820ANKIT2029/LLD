package parkinglot.strategy;

import java.util.List;
import java.util.Optional;
import parkinglot.entities.ParkingFloor;
import parkinglot.entities.ParkingSpot;
import parkinglot.entities.Vehicle;

public interface ParkingStrategy {
    public Optional<ParkingSpot> findSpot(List<ParkingFloor> parkingFloors, Vehicle vehicle);
}
