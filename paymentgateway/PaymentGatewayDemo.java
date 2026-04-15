package paymentgateway;

import java.util.Map;
import java.util.HashMap;

import paymentgateway.entities.*;
import paymentgateway.observer.*;
import paymentgateway.enums.*;


public class PaymentGatewayDemo {
    public static void main(String[] args) {
        // 1. Setup the gateway facade (Singleton)
        PaymentGatewayService paymentGateway = PaymentGatewayService.getInstance();

        // 2. Register observers to be notified of transaction events
        paymentGateway.addObserver(new MerchantPaymentObserver());
        paymentGateway.addObserver(new CustomerPaymentObserver());

        System.out.println("----------- SCENARIO 1: Successful Credit Card Payment -----------");

        // a. Merchant's backend creates a payment request using the Builder Pattern
        PaymentRequest ccRequest = new PaymentRequest.Builder()
                .setPayerId("U-123")
                .setAmount(150.75)
                .setCurrency("INR")
                .setPaymentMethod(PaymentMethod.CREDIT_CARD)
                .setPaymentDetails(Map.of("cardNumber", "1234-XXXX-XXXX-5678"))
                .build();

        // b. Merchant's backend sends it to the facade
        paymentGateway.processPayment(ccRequest);

        System.out.println("\n----------- SCENARIO 2: Successful PayPal Payment -----------");

        PaymentRequest paypalRequest = new PaymentRequest.Builder()
                .setPayerId("U-456")
                .setAmount(88.50)
                .setCurrency("USD")
                .setPaymentMethod(PaymentMethod.PAYPAL)
                .setPaymentDetails(Map.of("email", "user@example.com"))
                .build();

        paymentGateway.processPayment(paypalRequest);
    }
}