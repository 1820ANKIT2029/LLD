package paymentgateway.processor;

import paymentgateway.entities.PaymentRequest;
import paymentgateway.entities.PaymentResponse;
import paymentgateway.enums.PaymentStatus;

public class CreditCardPaymentProcessor extends AbstractPaymentProcessor {
    public CreditCardPaymentProcessor() {
        super();
    }

    protected PaymentResponse doProces(PaymentRequest request) {
        System.out.println("Processing credit card payment of amount " + 
                           request.getAmount() + " " + request.getCurrency());
        
        // Simulate interaction with Visa/Mastercard network
        return new PaymentResponse(PaymentStatus.SUCCESSFUL, "Credit Card payment successful.");
    }
}