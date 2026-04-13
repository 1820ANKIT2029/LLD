package parkinglot.entities;

import parkinglot.enums.VehicleSize;

public class ParkingSpot {
    private VehicleSize spotSize;
    private String spotId;
    private boolean isOccupied;
    private Vehicle parkedVehicle;

    public ParkingSpot(String spotId, VehicleSize spotSize) {
        this.spotSize = spotSize;
        this.spotId = spotId;
        this.isOccupied = false;
    }

    public String getSpotId() {return this.spotId;}
    public VehicleSize getSpotSize() {return this.spotSize;}
    public void setParkedVehicle(Vehicle parkedVehicle) {this.parkedVehicle = parkedVehicle;}
    public synchronized boolean isAvailable() {return !this.isOccupied;}
    public synchronized boolean isOccupied() {return this.isOccupied;}

    public synchronized boolean canFitVehicle(Vehicle vehicle) {
        if(this.isOccupied) return false;

        VehicleSize vehicleSize = vehicle.getVehicleSize();
        switch(vehicleSize) {
            case SMALL:
                return this.spotSize == VehicleSize.SMALL;
            case MEDIUM:
                return (this.spotSize == VehicleSize.MEDIUM || this.spotSize == VehicleSize.LARGE);
            case LARGE:
                return this.spotSize == VehicleSize.LARGE;
            default:
        }
        return false;
    }
    
    public synchronized void parkVehicle(Vehicle vehicle) {
        this.setParkedVehicle(vehicle);
        this.isOccupied = true;
    }

    public synchronized void unparkVehicle() {
        this.setParkedVehicle(null);
        this.isOccupied = false;
    }
    
}