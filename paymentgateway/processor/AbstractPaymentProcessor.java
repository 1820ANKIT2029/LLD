package paymentgateway.processor;

import paymentgateway.entities.PaymentRequest;
import paymentgateway.entities.PaymentResponse;
import paymentgateway.enums.PaymentStatus;

public abstract class AbstractPaymentProcessor implements PaymentProcessor {
    protected final int MAX_RETRIES = 3;

    protected abstract PaymentResponse doProces(PaymentRequest request);

    public PaymentResponse processPayment(PaymentRequest request) {
        int tries = this.MAX_RETRIES;
        PaymentResponse response = new PaymentResponse(PaymentStatus.FAILED, "Server Down");
        while(tries > 0) {
            response = doProces(request);
            if(response.getStatus() != PaymentStatus.FAILED) {
                break;
            }
            tries--;
        }

        return response;
    }
}