package com.retromania.game.tic_tac_toe.individuals;

import com.retromania.game.shared_abstractions.User;
import com.retromania.game.tic_tac_toe.utils.UserPrefrence;

import javax.inject.Inject;
import javax.inject.Named;

public class TicTacToe {
    CellManager cellManager;
    public String currentTurn;
    UserPrefrence userPrefrence;


    @Inject
    public TicTacToe(UserPrefrence userPrefrence, CellManager cellManager) {
//        TODO delete the gameWidth and gameHeight from cells logic
        this.userPrefrence = userPrefrence;
        this.cellManager = cellManager;
        this.currentTurn = "Cross";
    }

    public CellManager getCellManager() {
        return cellManager;
    }

    public void selectCell(int x, int y) {
        for (int i = 0; i < userPrefrence.getGameSize(); i++) {
            for (int j = 0; j < userPrefrence.getGameSize(); j++) {
                if (cellManager.cellArray[i][j].inCell(x, y)) {
                    if (!cellManager.cellArray[i][j].isTouched) {
                        cellManager.cellArray[i][j].isTouched = true;
                        cellManager.cellArray[i][j].setCell(currentTurn);
                        currentTurn = switchTurns(currentTurn);
                        System.out.println(Integer.toString(i) + " " + Integer.toString(j));
                    }
                }
            }
        }
    }

    public String switchTurns(String currentTurn) {
        if (currentTurn == "Cross") {
            currentTurn = "Circle";
        } else {
            currentTurn = "Cross";
        }
        return currentTurn;
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

    /* FUNCTIONS FOR AI implementation of TicTacToe */

//    public TicTacToe copyTicTacToe() {
//        TicTacToe newTicTacToe = new TicTacToe(gameWidth, gameHeight, userPrefrence);
//        newTicTacToe.cellManager = this.cellManager.copyCellManager();
//        newTicTacToe.currentTurn = this.currentTurn;
//        return newTicTacToe;
//    }

    public Cell[][] getCellStates() {
        return cellManager.cellArray;
    }

    public void changeCellState(int i, int j, String shape) {
        if (shape.equals("None")) {
            cellManager.cellArray[i][j].isTouched = false;
        } else {
            cellManager.cellArray[i][j].isTouched = true;
        }
        cellManager.cellArray[i][j].setCell(shape);
    }

    public String getStatus() {
        if (isEnd()) {                 //Game finished
            return getWinner();
        }
        return "Unfinished"; //Game not finished
    }
    /* FUNCTIONS END */

}
