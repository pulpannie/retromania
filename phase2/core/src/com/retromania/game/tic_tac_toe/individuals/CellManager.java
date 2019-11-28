package com.retromania.game.tic_tac_toe.individuals;

import com.retromania.game.tic_tac_toe.individuals.Cell;

public class CellManager{
    int gameWidth, gameHeight;
    int size;
    Cell[][] cellArray;
    String winner = "None";
    public CellManager(int gameWidth, int gameHeight, int size){
        this.gameWidth = gameWidth;
        this.gameHeight = gameHeight;
        this.size = size;
        cellArray = new Cell[size][size];
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                cellArray[i][j] = new Cell(gameWidth/size, gameHeight/size, gameWidth*i/size, gameHeight*j/size);
            }
        }
    }

    protected CellManager copyCellManager(){
        CellManager tmpCellManager = new CellManager(gameWidth, gameHeight, size);
        tmpCellManager.winner = this.winner;
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                tmpCellManager.cellArray[i][j] = cellArray[i][j].copyCell();
            }
        }

        return tmpCellManager;
    }

    public boolean checkRow(int n){
        for (int i = 0; i < size; i++){
            int tmp = 0;
            for (int j = 0; j < size-1; j++){
                if (cellArray[i][j].equal(cellArray[i][j+1])){
                    tmp++;
                    if(tmp == n-1){
                        winner = cellArray[i][j].getCell();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkColumn(int n){
        for (int i = 0; i < size; i++){
            int tmp = 0;
            for (int j = 0; j < size-1; j++){
                if (cellArray[j][i].equal(cellArray[j+1][i])){
                    tmp++;
                    if(tmp == n-1){
                        winner = cellArray[j][i].getCell();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkDiagRight(int n){
        int tmp = 0;
        for (int i = 0; i < size-1; i++){
                if (cellArray[i][i].equal(cellArray[i+1][i+1])){
                    tmp++;
                    if(tmp == n-1){
                        winner = cellArray[i][i].getCell();
                        return true;
                    }
                }
            }
        return false;
    }

    public boolean checkDiagLeft(int n){
        int tmp = 0;
        for (int i = 0; i < size-1; i++){
                if (cellArray[i][size-i-1].equal(cellArray[i+1][size-i-2])){
                    tmp++;
                    if(tmp == n-1){
                        winner = cellArray[i][size-i-1].getCell();
                        return true;
                    }
                }
            }
        return false;
    }

    public boolean allTouched(){
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if (cellArray[i][j].getCell().equals("None")){
                    return false;
                }
            }
        }
        return true;
    }

    private void setCellsWidth(){
    }
    private void setCellsX(){

    }
    private void setCellsY(){

    }


}
