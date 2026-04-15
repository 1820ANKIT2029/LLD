package paymentgateway.enums;

public enum PaymentStatus {
    INITIATED("INITIATED"),
    SUCCESSFUL("SUCCESSFUL"),
    FAILED("FAILED");

    private String value;

    private PaymentStatus(String value) {
        this.value = value;
    }

    public String getValue() {return this.value;}
}