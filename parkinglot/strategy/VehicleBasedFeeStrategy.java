package parkinglot.strategy;

import java.util.Map;
import java.util.HashMap;
import parkinglot.entities.ParkingTicket;
import parkinglot.enums.VehicleSize;

public class VehicleBasedFeeStrategy implements FeeStrategy {
    private final Map<VehicleSize, Double> HOURLY_RATE = Map.of(
            VehicleSize.SMALL, 10.0,
            VehicleSize.MEDIUM, 20.0,
            VehicleSize.LARGE, 30.0
        );

    public Double calculateFee(ParkingTicket ticket) {
        Long dur = ticket.getExitTimestamp() - ticket.getExitTimestamp();
        Long hours = dur / (1000 * 60 * 60) + 1;
        return HOURLY_RATE.get(ticket.getVehicle().getVehicleSize()) * hours;
    }
}