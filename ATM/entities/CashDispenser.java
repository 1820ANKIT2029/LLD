package ATM.entities;

import ATM.chain.DispenseChain;

public class CashDispenser {
    private DispenseChain chain;

    public CashDispenser(DispenseChain chain) {
        this.chain = chain;
    }

    public synchronized void dispenseCash(int amount) {
        this.chain.dispense(amount);
    }

    public synchronized boolean canDispenseCash(int amount) {
        if(amount % 10 != 0) return false;
        return this.chain.canDispense(amount);
    }
}