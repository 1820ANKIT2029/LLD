package paymentgateway.processor;

import paymentgateway.entities.PaymentRequest;
import paymentgateway.entities.PaymentResponse;

public interface PaymentProcessor {
    public PaymentResponse processPayment(PaymentRequest request);
}