package parkinglot.strategy;

import parkinglot.entities.ParkingTicket;

public class FlatRateFeeStrategy implements FeeStrategy {
    private final Double RATE_PER_HOUR = 10.0;

    public Double calculateFee(ParkingTicket ticket) {
        Long dur = ticket.getExitTimestamp() - ticket.getExitTimestamp();
        Long hours = dur / (1000 * 60 * 60) + 1;
        return RATE_PER_HOUR * hours;
    }
}
