package ATM.entities;

import java.util.Map;
import java.util.HashMap;

public class Account {
    private String accountNumber;
    private Double balance;
    private Map<String, Card> cards;

    public Account(String accountNumber, Double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.cards = new HashMap<>();
    }

    public void addCard(Card card) {
        cards.put(card.getCardNumber(), card);
    }

    public String getAcccountNumber() {return this.accountNumber;}
    public synchronized Double getBalance() {return this.balance;}
    public Map<String, Card> getCards() {return this.cards;}

    public synchronized boolean withdraw(Double amt) {
        if(amt < 0.0 || amt > this.balance) return false;

        this.balance -= amt;
        return true;
    }

    public synchronized void deposit(Double amt){
        if(amt < 0.0) return;

        this.balance += amt;
    }
}