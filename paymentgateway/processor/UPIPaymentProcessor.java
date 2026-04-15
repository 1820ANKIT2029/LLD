package paymentgateway.processor;

import paymentgateway.entities.PaymentRequest;
import paymentgateway.entities.PaymentResponse;
import paymentgateway.enums.PaymentStatus;

public class UPIPaymentProcessor extends AbstractPaymentProcessor {
    public UPIPaymentProcessor() {
        super();
    }

    protected PaymentResponse doProces(PaymentRequest request) {
        System.out.println("Processing UPI payment of " + 
                           request.getAmount() + " " + request.getCurrency());
        
        // Simulate UPI VPA validation and transaction
        return new PaymentResponse(PaymentStatus.SUCCESSFUL, "UPI payment successful.");
    }
}