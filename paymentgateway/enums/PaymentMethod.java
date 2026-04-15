package paymentgateway.enums;

public enum PaymentMethod {
    CREDIT_CARD("CREDIT_CARD"),
    PAYPAL("PAYPAL"),
    UPI("UPI");

    private String value;

    private PaymentMethod(String value) {
        this.value = value;
    }

    public String getValue() {return this.value;}
}