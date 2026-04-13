package parkinglot.entities;

import parkinglot.enums.VehicleSize;

public abstract class Vehicle {
    private VehicleSize size;
    private String licenseNumber;

    public Vehicle(VehicleSize size, String licenseNumber) {
        this.size = size;
        this.licenseNumber = licenseNumber;
    }

    public VehicleSize getVehicleSize() {return this.size;}
    public String getLicenseNumber() {return this.licenseNumber;}
}