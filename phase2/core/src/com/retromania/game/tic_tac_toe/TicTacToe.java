package com.retromania.game.tic_tac_toe;

public class TicTacToe {
    CellManager cellManager;
    String currentTurn;
    int gameWidth, gameHeight;

    public TicTacToe(int gameWidth, int gameHeight){
        this.cellManager = new CellManager(gameWidth, gameHeight);
        this.currentTurn = "Cross";
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
    }

    public CellManager getCellManager(){
        return cellManager;
    }

    /*NEED TO FIX*/
    public TicTacToe copyTicTacToe(){
        TicTacToe newTicTacToe = new TicTacToe(gameWidth, gameHeight);
        return newTicTacToe;
    }

    public Cell[][] getCellStates(){
        return cellManager.cellArray;
    }

    public void selectCell(int x, int y){
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

    public boolean isEnd(){
        if(cellManager.checkRow(3) || cellManager.checkColumn(3) ||
                cellManager.checkDiagLeft(3) || cellManager.checkDiagRight(3) || cellManager.allTouched()){
            return true;
        }
        return false;
    }


    public String getWinner(){
            return cellManager.winnerCell.getCell();
    }
}
