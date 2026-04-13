package parkinglot.strategy;

import parkinglot.entities.ParkingTicket;

public interface FeeStrategy {
    public Double calculateFee(ParkingTicket parkingTicket);
}