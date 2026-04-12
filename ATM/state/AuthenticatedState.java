package ATM.state;

import ATM.entities.ATMSystem;
import ATM.enums.OperationType;

public class AuthenticatedState implements ATMState {
    public void insertCard(ATMSystem atm, String cardID) {
        System.out.println("Error: A card is already inserted and a session is active.");
    }

    public void enterPin(ATMSystem atm, String pin) {
        System.out.println("Error: PIN has already been entered and authenticated.");
    }

    public void selectOperation(ATMSystem atm, OperationType operationType, int[] args) {
        int amt;
        switch(operationType) {
            case OperationType.CHECK_BALANCE:
                atm.checkBalance();
                break;
            case OperationType.WITHDRAW_CASH:
                if(args.length == 0 || args[0] <= 0.0) {
                    System.out.println("Error: Invalid withdrawal amount specified.");
                    return;
                }

                amt = args[0];
                System.out.println("Processing withdrawal for " + amt);
                try {
                    atm.withdrawCash(amt);
                } catch(Exception e) {
                    System.out.println("Error: Insufficient balance.");
                    return;
                }
                break;
            case OperationType.DEPOSIT_CASH:
                if(args.length == 0 || args[0] <= 0.0) {
                    System.out.println("Error: Invalid deposit amount specified.");
                    return;
                }
                amt = args[0];
                System.out.println("Processing withdrawal for " + amt);
                atm.depositCash(amt);
                break;
            default:
                System.out.println("Error: Invalid operation selected.");
                return;

        }
        System.out.println("Transaction complete.");
        this.ejectCard(atm);
    }

    public void ejectCard(ATMSystem atm) {
        System.out.println("Ending session. Card has been ejected. Thank you for using our ATM.");
        atm.setCurrentCard(null);
        atm.changeState(new IdleState());
    }
}