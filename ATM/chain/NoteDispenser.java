package ATM.chain;

public class NoteDispenser implements DispenseChain {
    private int noteValue;
    private int numNotes;
    private DispenseChain nextChain;

    public NoteDispenser(int noteValue, int numNotes) {
        this.noteValue = noteValue;
        this.numNotes = numNotes;
    }

    public void setNextChain(DispenseChain nextChain) {
        this.nextChain = nextChain;
    }

    public synchronized boolean canDispense(int amt) {
        if(amt < 0) return false;
        if(amt == 0) return true;

        int cash = amt / this.noteValue;
        cash = Math.min(cash, this.numNotes);
        int remainingAmt = amt - (cash * this.noteValue);

        if(remainingAmt == 0) return true;
        if(this.nextChain != null) return this.nextChain.canDispense(remainingAmt);
        return false;
    }

    public synchronized void dispense(int amt) {
        int cash = amt / this.noteValue;
        cash = Math.min(cash, this.numNotes);

        if(cash > 0) {
            amt -= (cash * this.noteValue);
            this.numNotes -= cash;
            System.out.println(
                "Dispensing " + cash + " notes of ₹" + this.noteValue
            );
        }
        
        if(amt > 0 && this.nextChain != null) this.nextChain.dispense(amt);
        return;
    }
}