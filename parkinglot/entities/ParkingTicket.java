package parkinglot.entities;

import java.util.UUID;
import parkinglot.enums.VehicleSize;

public class ParkingTicket {
    private ParkingSpot spot;
    private String ticketId;
    private Long entryTimestamp; 
    private Long exitTimestamp;
    private Vehicle vehicle;

    public ParkingTicket(Vehicle vehicle, ParkingSpot spot) {
        this.vehicle = vehicle;
        this.spot = spot;
        this.ticketId = UUID.randomUUID().toString();
        this.entryTimestamp = System.currentTimeMillis();
        this.exitTimestamp = 0L;
    }

    public String getTicketId() {return this.ticketId;}
    public ParkingSpot getSpot() {return this.spot;}
    public Vehicle getVehicle() {return this.vehicle;}
    public Long getEntryTimestamp() {return this.entryTimestamp;}
    public Long getExitTimestamp() {return this.exitTimestamp;}
    public void setExitTimestamp() {this.exitTimestamp = System.currentTimeMillis();}
}