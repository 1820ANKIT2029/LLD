package ATM.entities;

import java.util.Map;
import java.util.HashMap;

public class BankService {
    private Map<String, Card> cards;
    private Map<Card, Account> cardAccountMap;
    private Map<String, Account> accounts;

    public BankService() {
        this.cards = new HashMap<>();
        this.cardAccountMap = new HashMap<>();
        this.accounts = new HashMap<>();

        // Create sample accounts and cards
        Account account1 = this.createAccount("1234567890", 1000.0);
        Card card1 = this.createCard("1234-5678-9012-3456", "1234");
        this.linkCardToAccount(card1, account1);
        
        Account account2 = this.createAccount("9876543210", 500.0);
        Card card2 = this.createCard("9876-5432-1098-7654", "4321");
        this.linkCardToAccount(card2, account2);
    }

    public void linkCardToAccount(Card card, Account account) {
        account.addCard(card);
        this.cardAccountMap.put(card, account);
    }

    public Double getBalance(Card card) {
        Account account = this.cardAccountMap.get(card);
        if(account == null) return 0.0;

        return account.getBalance();
    }

    public void depositMoney(Card card, Double amt) {
        Account acc = this.cardAccountMap.get(card);
        if(acc == null) return;

        acc.deposit(amt);
    }

    public Account createAccount(String accountNumber, Double amt) {
        Account account = new Account(accountNumber, amt);
        this.accounts.put(accountNumber, account);

        return account;
    }

    public Card createCard(String cardID, String pin) {
        Card card = new Card(cardID, pin);
        this.cards.put(cardID, card);

        return card;
    }

    public Card getCard(String cardID) {
        return this.cards.get(cardID);
    }

    public boolean withdrawMoney(Card card, Double amt) {
        Account acc = this.cardAccountMap.get(card);
        if(acc == null) return false;

        return acc.withdraw(amt);
    }

    public boolean authenticate(Card card, String pin) {
        return pin.equals(card.getPin());
    }

    public Card authenticateCard(String cardID) {
        return cards.get(cardID);
    }
}