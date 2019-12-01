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
    public TicTacToe(UserPrefrence userPrefrence) {
//        TODO delete the gameWidth and gameHeight from cells logic
        this.userPrefrence = userPrefrence;
        this.cellManager = new CellManager(userPrefrence);
        this.currentTurn = "Cross";
    }

    public CellManager getCellManager() {
        return cellManager;
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
