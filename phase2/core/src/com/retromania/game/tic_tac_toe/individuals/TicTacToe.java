package com.retromania.game.tic_tac_toe.individuals;

import com.retromania.game.tic_tac_toe.utils.UserPreference;

import javax.inject.Inject;

public class TicTacToe {
    CellManager cellManager;
    public String currentTurn;
    UserPreference userPreference;


    @Inject
    public TicTacToe(UserPreference userPreference) {
//        TODO delete the gameWidth and gameHeight from cells logic
        this.userPreference = userPreference;
        this.cellManager = new CellManager(userPreference);
        this.currentTurn = "Cross";
    }

    public CellManager getCellManager() {
        return cellManager;
    }

    public boolean isCellTouched(int i, int j){
        return cellManager.isCellTouched(i,j);
    }

    public String getCellState(int i, int j){
        return cellManager.getCellState(i, j);
    }

    public void touchCell(int i, int j) {
        if(!cellManager.isCellTouched(i,j)){
            cellManager.touchCell(i, j, currentTurn);
            switchTurns();
            System.out.println(currentTurn);
        }
    }

    public void switchTurns() {
        if (currentTurn.equals("Cross")) {
            currentTurn = "Circle";
        } else {
            currentTurn = "Cross";
        }
    }

    public boolean isEnd() {
        if (cellManager.checkRow(3) || cellManager.checkColumn(3) ||
                cellManager.checkDiagLeft(3) || cellManager.checkDiagRight(3) || cellManager.allTouched()) {
            return true;
        }
        return false;
    }


    public String getWinner() {
        return cellManager.winner;
    }

    public Cell[][] getCellStates() {
        return cellManager.cellArray;
    }


}
