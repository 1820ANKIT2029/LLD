package paymentgateway.observer;

import paymentgateway.entities.Transaction;

public class MerchantPaymentObserver implements PaymentObserver {
    @Override
    public void onTransactionUpdate(Transaction transaction) {
        System.out.println("--- MERCHANT NOTIFICATION ---");
        System.out.println("Transaction " + transaction.getId() + 
                           " status updated to: " + transaction.getStatus());
        System.out.println("-----------------------------");
    }
}