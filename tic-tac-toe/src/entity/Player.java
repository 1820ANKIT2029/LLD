package entity;

import enums.Symbol;
import exception.InvalidMoveException;

public class Player {
    private String name;
    private Symbol symbol;

    public Player(String name, Symbol symbol) {
        if(symbol == Symbol.EMPTY) {
            throw new InvalidMoveException("Empty Symbol not allow");
        }
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return this.name;
    }

    public Symbol getSymbol() {
        return this.symbol;
    }

    public String toString() {
        return this.name + " (" + this.symbol.getChar() + ")";
    }
}