package paymentgateway.entities;

import java.time.LocalDateTime;
import paymentgateway.enums.PaymentStatus;

public class Transaction {
    private String id;
    private PaymentRequest request;
    private PaymentStatus status;
    private LocalDateTime timestamp;

    public Transaction(PaymentRequest request) {
        this.id = request.getTransactionId();
        this.request = request;
        this.status = PaymentStatus.INITIATED;
        this.timestamp = LocalDateTime.now();
    }

    public void setStatus(PaymentStatus status) {this.status = status;}
    public PaymentStatus getStatus() {return this.status;}
    public PaymentRequest getRequest() {return this.request;}
    public LocalDateTime getTimestamp() {return this.timestamp;}
    public String getId() {return this.id;}
}