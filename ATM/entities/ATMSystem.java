package ATM.entities;

import java.util.concurrent.atomic.AtomicLong;
import ATM.state.*;
import ATM.chain.*;
import ATM.enums.OperationType;

public class ATMSystem {
    private static volatile ATMSystem instance;
    private static final Object lock = new Object();

    private AtomicLong transactionCounter;
    private ATMState currentState;
    private Card currentCard;
    private CashDispenser cashDispenser;
    private BankService bankService;

    public ATMSystem() {
        this.transactionCounter = new AtomicLong();
        this.currentState = new IdleState();
        this.currentCard = null;
        this.bankService = new BankService();

        NoteDispenser100 c1 = new NoteDispenser100(10);
        NoteDispenser50 c2 = new NoteDispenser50(20);
        NoteDispenser20 c3 = new NoteDispenser20(30);
        c1.setNextChain(c2);
        c2.setNextChain(c3);
        this.cashDispenser = new CashDispenser(c1);
    }

    public static ATMSystem getInstance() {
        if(instance == null) {
            synchronized (lock)  {
                if(instance == null){
                    instance = new ATMSystem();
                }
            }
        }

        return instance;
    }

    public void changeState(ATMState newState) {
        this.currentState = newState;
    }

    public void setCurrentCard(Card card) {
        this.currentCard = card;
    }

    public void insertCard(String cardID) {
        this.currentState.insertCard(this, cardID);
    }

    public void enterPin(String pin) {
        this.currentState.enterPin(this, pin);
    }

    public void selectOperation(OperationType op, int[] args) {
        this.currentState.selectOperation(this, op, args);
    }

    public void checkBalance() {
        Double balance = this.bankService.getBalance(this.currentCard);
        System.out.println("Your current account balance is: ₹" + balance);
    }

    public void withdrawCash(int amt) {
        if(this.cashDispenser.canDispenseCash(amt)) {
            throw new RuntimeException("Insufficient cash available in the ATM.");
        }

        try {
            this.cashDispenser.dispenseCash(amt);
        } catch(Exception e) {
            this.bankService.depositMoney(this.currentCard, (double) amt);
            throw e;
        }
    }

    public void depositCash(int amt) {
        this.bankService.depositMoney(this.currentCard, (double) amt);
    }

    public Card getCurrentCard() {
        return this.currentCard;
    }

    public BankService getBankService() {
        return this.bankService;
    }
}