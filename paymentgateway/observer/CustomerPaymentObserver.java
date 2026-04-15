package paymentgateway.observer;

import paymentgateway.entities.Transaction;
import paymentgateway.enums.PaymentStatus;

public class CustomerPaymentObserver implements PaymentObserver {
    @Override
    public void onTransactionUpdate(Transaction transaction) {
        if (transaction.getStatus() == PaymentStatus.SUCCESSFUL) {
            System.out.println("--- CUSTOMER EMAIL ---");
            System.out.println("Your payment of " + transaction.getRequest().getAmount() + 
                               " was successful. Transaction ID: " + transaction.getId());
            System.out.println("----------------------");
        }
    }
}