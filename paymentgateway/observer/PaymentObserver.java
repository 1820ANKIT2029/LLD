package paymentgateway.observer;

import paymentgateway.entities.Transaction;

public interface PaymentObserver {
    public void onTransactionUpdate(Transaction transaction);
}