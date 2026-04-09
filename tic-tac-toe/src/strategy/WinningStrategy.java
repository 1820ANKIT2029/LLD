package strategy;

import entity.Board;
import enums.Symbol;

public interface WinningStrategy {
    public boolean checkWin(Board board, int row, int col, Symbol symbol);
}