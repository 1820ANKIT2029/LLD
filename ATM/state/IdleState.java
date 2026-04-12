package ATM.state;

import ATM.entities.ATMSystem;
import ATM.entities.Card;
import ATM.enums.OperationType;

public class IdleState implements ATMState {
    public void insertCard(ATMSystem atm, String cardID) {
        System.out.println("\nCard has been inserted");
        Card card = atm.getBankService().authenticateCard(cardID);

        if(card == null) {
            this.ejectCard(atm);
        }
        else {
            atm.setCurrentCard(card);
            atm.changeState(new HasCardState());
        }
    }

    public void enterPin(ATMSystem atm, String pin) {
        System.out.println("Error: Please insert a card first.");
    }

    public void selectOperation(ATMSystem atm, OperationType operationType, int[] args) {
        System.out.println("Error: Please insert a card first.");
    }

    public void ejectCard(ATMSystem atm) {
        System.out.println("Error: Card not found.");
        atm.setCurrentCard(null);
    }
}