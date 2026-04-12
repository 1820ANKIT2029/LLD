package ATM.state;

import ATM.entities.ATMSystem;
import ATM.entities.Card;
import ATM.enums.OperationType;

public class HasCardState implements ATMState {
    public void insertCard(ATMSystem atm, String cardID) {
        System.out.println("Error: A card is already inserted. Cannot insert another card.");
    }

    public void enterPin(ATMSystem atm, String pin) {
        System.out.println("Authenticating PIN...");
        Card card = atm.getCurrentCard();
        boolean isAuthenticated = atm.getBankService().authenticate(card, pin);

        if(isAuthenticated) {
            System.out.println("Authentication successful.");
            atm.changeState(new AuthenticatedState());
        }
        else {
            System.out.println("Authentication failed: Incorrect PIN.");
            this.ejectCard(atm);
        }
    }

    public void selectOperation(ATMSystem atm, OperationType operationType, int[] args) {
        System.out.println("Error: Please enter your PIN first to select an operation.");
    }

    public void ejectCard(ATMSystem atm) {
        System.out.println("Card has been ejected. Thank you for using our ATM.");
        atm.setCurrentCard(null);
        atm.changeState(new IdleState());
    }
}