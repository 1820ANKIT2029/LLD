package ATM.entities;

public class Card {
    private String cardNumber;
    private String pin;

    public Card(String cardNumber, String pin) {
        this.cardNumber = cardNumber;
        this.pin = pin;
    }

    public String getCardNumber() {return this.cardNumber;}
    public String getPin() {return this.pin;}
}