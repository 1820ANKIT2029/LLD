package paymentgateway.processor;

import paymentgateway.enums.PaymentMethod;

public class PaymentProcessorFactory {
    public static PaymentProcessor getProcessor(PaymentMethod paymentMethod) {
        PaymentProcessor processor;
        switch(paymentMethod) {
            case CREDIT_CARD:
                return new CreditCardPaymentProcessor();
            case UPI:
                return new UPIPaymentProcessor();
            case PAYPAL:
                return new PayPalPaymentProcessor();
            default:
        }

        return null;
    }
}