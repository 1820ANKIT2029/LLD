package paymentgateway.entities;

import java.util.Map;
import java.util.UUID;
import paymentgateway.enums.PaymentMethod;

public class PaymentRequest {
    private String transactionId;
    private String payerId;
    private Double amount;
    private String currency;
    private PaymentMethod paymentMethod;
    private Map<String, String> paymentDetails;

    private PaymentRequest(Builder builder) {
        this.transactionId = UUID.randomUUID().toString();
        this.payerId = builder.payerId;
        this.amount = builder.amount;
        this.currency = builder.currency;
        this.paymentMethod = builder.paymentMethod;
        this.paymentDetails = builder.paymentDetails;
    }

    public String getTransactionId() {return this.transactionId;}
    public String getPayerId() {return this.payerId;}
    public Double getAmount() {return this.amount;}
    public String getCurrency() {return this.currency;}
    public PaymentMethod getPaymentMethod() {return this.paymentMethod;}
    public Map<String, String> getPaymentDetails() {return this.paymentDetails;}

    public static class Builder {
        private String payerId;
        private Double amount;
        private String currency;
        private PaymentMethod paymentMethod;
        private Map<String, String> paymentDetails;

        public Builder setPayerId(String payerId) {
            this.payerId = payerId;
            return this;
        }
        public Builder setAmount(Double amount) {
            this.amount = amount;
            return this;
        }
        public Builder setCurrency(String currency) {
            this.currency = currency;
            return this;
        }
        public Builder setPaymentMethod(PaymentMethod paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }
        public Builder setPaymentDetails(Map<String, String> paymentDetails) {
            this.paymentDetails = paymentDetails;
            return this;
        }
        public PaymentRequest build() {
            return new PaymentRequest(this);
        }
    } 
}