package ATM.chain;

public interface DispenseChain {
    public void dispense(int amount);
    public void setNextChain(DispenseChain nextChain);
    public boolean canDispense(int amount);
}