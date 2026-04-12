package ATM.state;

import ATM.entities.ATMSystem;
import ATM.enums.OperationType;

public interface ATMState {
    public void insertCard(ATMSystem atm, String cardID);
    public void enterPin(ATMSystem atm, String pin);
    public void selectOperation(ATMSystem atm, OperationType operationType, int[] args);
    public void ejectCard(ATMSystem atm);
}