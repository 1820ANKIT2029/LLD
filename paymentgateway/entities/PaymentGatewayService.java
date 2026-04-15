package paymentgateway.entities;

import java.util.List;
import java.util.ArrayList;

import paymentgateway.enums.PaymentStatus;
import paymentgateway.observer.PaymentObserver;
import paymentgateway.processor.PaymentProcessorFactory;
import paymentgateway.processor.PaymentProcessor;

public class PaymentGatewayService {
    private static volatile PaymentGatewayService instance;
    private static final Object lock = new Object();

    private List<PaymentObserver> observers;

    public PaymentGatewayService() {
        this.observers = new ArrayList<>();
    }

    public static PaymentGatewayService getInstance() {
        if(instance == null) {
            synchronized(lock) {
                if(instance == null) {
                    instance = new PaymentGatewayService();
                }
            }
        }

        return instance;
    }

    public Transaction processPayment(PaymentRequest request) {
        Transaction transaction = new Transaction(request);
        try {
            PaymentProcessor processor = PaymentProcessorFactory.getProcessor(request.getPaymentMethod());
            PaymentResponse response = processor.processPayment(request);
            transaction.setStatus(response.getStatus());
        } catch (Exception e) {
            System.out.println("Payment processing failed: " + e.getMessage());
            transaction.setStatus(PaymentStatus.FAILED);
        }

        this.notifyObserver(transaction);
        return transaction;
    }

    public void addObserver(PaymentObserver observer) {
        this.observers.add(observer);
    }
    public void removeObserver(PaymentObserver observer) {
        this.observers.remove(observer);
    }
    private void notifyObserver(Transaction transaction) {
        for(PaymentObserver observer: this.observers) {
            observer.onTransactionUpdate(transaction);
        }
    }
}