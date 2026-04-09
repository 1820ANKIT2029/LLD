package enums;

public enum Symbol {
    X('X'), 
    O('O'), 
    EMPTY('-');

    private final char symbol;

    private Symbol(char symbol) {
        this.symbol = symbol;
    }

    public char getChar() {
        return this.symbol;
    }
}