package com.retromania.game.tic_tac_toe;

import com.badlogic.gdx.graphics.Texture;

public class TicTacToeLogic {
    CellManager cellManager;
    int index;
    boolean nowinner = false;

    public TicTacToeLogic(CellManager cellManager){
        this.cellManager = cellManager;
    }

    public boolean isEnd(){
        if(cellManager.checkRow(3) || cellManager.checkColumn(3) ||
                cellManager.checkDiagLeft(3) || cellManager.checkDiagRight(3)){
            return true;
        }
        else if (cellManager.allTouched()){
            nowinner = true;
            return true;
        }
        return false;
    }


    public String winner(){
        return cellManager.winnerCell.getCell();
    }
}
