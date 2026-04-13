package parkinglot.enums;

public enum VehicleSize {
    SMALL("Small vehicle"),
    MEDIUM("Medium vehicle"),
    LARGE("Large vehicle");

    private final String value;

    private VehicleSize(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
