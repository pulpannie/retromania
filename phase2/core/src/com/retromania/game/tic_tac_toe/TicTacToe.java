package com.retromania.game.tic_tac_toe;

public class TicTacToe {
    CellManager cellManager;
    String currentTurn;

    public TicTacToe(CellManager cellManager){
        this.cellManager = cellManager;
        this.currentTurn = "Cross";
    }
    public void clickCell(int x, int y){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++){
                if (cellManager.cellArray[i][j].inCell(x,y)) {
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

    public String switchTurns(String currentTurn){
        if (currentTurn == "Cross"){
            currentTurn = "Circle";
        }
        else{
            currentTurn = "Cross";
        }
        return currentTurn;
    }
}
