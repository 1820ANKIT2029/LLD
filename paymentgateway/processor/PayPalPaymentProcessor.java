package paymentgateway.processor;

import paymentgateway.entities.PaymentRequest;
import paymentgateway.entities.PaymentResponse;
import paymentgateway.enums.PaymentStatus;

public class PayPalPaymentProcessor extends AbstractPaymentProcessor {
    public PayPalPaymentProcessor() {
        super();
    }

    protected PaymentResponse doProces(PaymentRequest request) {
        System.out.println("Redirecting to PayPal for transaction " + request.getTransactionId());
        
        // Simulate PayPal API interaction
        return new PaymentResponse(PaymentStatus.SUCCESSFUL, "Paypal payment successful.");
    }
}