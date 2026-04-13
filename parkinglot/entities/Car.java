package parkinglot.entities;

import parkinglot.enums.VehicleSize;

public class Car extends Vehicle {
    public Car(String licenseNumber){
        super(VehicleSize.MEDIUM, licenseNumber);
    }
}