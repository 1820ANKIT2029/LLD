package paymentgateway.entities;

import paymentgateway.enums.PaymentStatus;

public class PaymentResponse {
    private PaymentStatus status;
    private String message;

    public PaymentResponse(PaymentStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getMessage() {return this.message;}
    public PaymentStatus getStatus() {return this.status;}
}