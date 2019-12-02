package com.retromania.game.tic_tac_toe.models;

import com.retromania.game.tic_tac_toe.utils.UserPreference;

import javax.inject.Inject;

/**
 * Model responsible for one TicTacToe game.
 * @author Hyokyung Kim
 */
public class TicTacToe {
    private CellManager cellManager;
    private String currentTurn;

    /**
     * constructor, uses Dagger
     * @param userPreference Current user's TicTacToe game's preference
     * instantiates CellManager class.
     */
    @Inject
    public TicTacToe(UserPreference userPreference) {
        this.cellManager = new CellManager(userPreference);
        this.currentTurn = "Cross";
    }

    /**
     * @param i which row the Cell to be touched is in.
     * @param j which column the Cell to be touched is in.
     * @return String whether the specific Cell is "Cross", "Circle", or "None".
     */
    public String getCellState(int i, int j){
        return cellManager.getCellState(i, j);
    }

    /**
     * @param i which row the Cell to be touched is in.
     * @param j which column the Cell to be touched is in.
     * @return boolean whether the specific Cell has been touched.
     */
    public boolean isCellTouched(int i, int j){
        return cellManager.isCellTouched(i,j);
    }

    /**
     * @param i which row the Cell to be touched is in.
     * @param j which column the Cell to be touched is in.
     */
    public void touchCell(int i, int j) {
        if(!cellManager.isCellTouched(i,j)){
            cellManager.touchCell(i, j, currentTurn);
            switchTurns();
            System.out.println(currentTurn);
        }
    }

    /**
     * switches the current turn.
     */
    public void switchTurns() {
        if (currentTurn.equals("Cross")) {
            currentTurn = "Circle";
        } else {
            currentTurn = "Cross";
        }
    }

    /**Checks if game has ended */
    public boolean isEnd() {
        if (cellManager.checkRow() || cellManager.checkColumn() ||
                cellManager.checkDiagLeft() || cellManager.checkDiagRight() || cellManager.allTouched()) {
            return true;
        }
        return false;
    }

    public String getWinner() {
        return cellManager.getWinner();
    }

}
