package com.retromania.game.tic_tac_toe.models;

import com.retromania.game.tic_tac_toe.utils.UserPreference;


/**
 * Model responsible for managing the Cells on the TicTacToe board.
 *
 * @author Hyokyung Kim
 */
public class CellManager {
    private Cell[][] cellArray;
    private String winner = "None";
    private UserPreference userPreference;

    /**
     * @param userPreference Current user's TicTacToe game's preference.
     * Instantiates all the Cells in the TicTacToe game.
     */
    public CellManager(UserPreference userPreference) {
        this.userPreference = userPreference;
        show();
    }

    /**
     * Instantiates all the Cells in the TicTacToe game, saves it in a two-dimensional array,
     * with the size specified in userPreference.
     */
    public void show() {
        cellArray = new Cell[userPreference.getGameSize()][userPreference.getGameSize()];
        for (int i = 0; i < userPreference.getGameSize(); i++) {
            for (int j = 0; j < userPreference.getGameSize(); j++) {
                cellArray[i][j] =
                        new Cell();
            }
        }
    }

    /**
     * Touches a specific Cell.
     * @param i which row the Cell to be touched is in.
     * @param j which column the Cell to be touched is in.
     * @param currentTurn who's turn it currently is.
     */
    public void touchCell(int i, int j, String currentTurn) {
        cellArray[i][j].setIsTouched(true);
        cellArray[i][j].setCell(currentTurn);
    }

    /**
     * Checks whether a specific Cell has been touched.
     * @param i which row the Cell is in.
     * @param j which column the Cell is in.
     * @return whether the Cell has been touched.
     */
    public boolean isCellTouched(int i, int j) {
        return cellArray[i][j].getIsTouched();
    }

    /**
     * Gets the state of a specific Cell.
     * @param i which row the Cell is in.
     * @param j which column the Cell is in.
     * @return the state of the Cell. "Cross", "Circle", or "None".
     */
    public String getCellState(int i, int j) {
        return cellArray[i][j].getCell();

    }

    /**
     * gets the winner of the game.
     * @return the winner.
     */
    public String getWinner(){
        return winner;
    }

    /**
     * checks if there is are three consecutive Cells with the same state, vertically.
     * @return true if it is the case.
     */
    public boolean checkColumn() {
        for (int i = 0; i < userPreference.getGameSize(); i++) {
            int tmp = 0;
            for (int j = 0; j < userPreference.getGameSize() - 1; j++) {
                if (cellArray[i][j].equal(cellArray[i][j + 1])) {
                    tmp++;
                    if (tmp == 2) {
                        winner = cellArray[i][j].getCell();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * checks if there is are three consecutive Cells with the same state, horizontally.
     * @return true if it is the case.
     */
    public boolean checkRow() {
        for (int i = 0; i < userPreference.getGameSize(); i++) {
            int tmp = 0;
            for (int j = 0; j < userPreference.getGameSize() - 1; j++) {
                if (cellArray[j][i].equal(cellArray[j + 1][i])) {
                    tmp++;
                    if (tmp == 2) {
                        winner = cellArray[j][i].getCell();
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * checks if there is are three consecutive Cells with the same state, diagonally going down left.
     * @return true if it is the case.
     */
    public boolean checkDiagLeft() {
        for (int i = 0; i < userPreference.getGameSize() - 2; i++) {
            for (int j = 0; j < userPreference.getGameSize() - 2; j++) {
                if (cellArray[i][j].equal(cellArray[i + 1][j + 1]) && cellArray[i + 1][j + 1].equal(cellArray[i + 2][j + 2])) {
                    winner = cellArray[i][j].getCell();
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * checks if there is are three consecutive Cells with the same state, diagonally going down right.
     * @return true if it is the case.
     */
    public boolean checkDiagRight() {
        for (int i = 0; i < userPreference.getGameSize() - 2; i++) {
            for (int j = 0; j < userPreference.getGameSize() - 2; j++) {
                if (cellArray[j][userPreference.getGameSize() - i - 1].equal(
                        cellArray[j + 1][userPreference.getGameSize() - i - 2]) && (
                        cellArray[j + 1][userPreference.getGameSize() - i - 2]).equal(cellArray[j + 2][userPreference.getGameSize() - i - 3])) {
                    winner = cellArray[j][userPreference.getGameSize() - i - 1].getCell();
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * checks if all the Cells have been touched.
     * @return true if it is the case.
     */
    public boolean allTouched() {
        for (int i = 0; i < userPreference.getGameSize(); i++) {
            for (int j = 0; j < userPreference.getGameSize(); j++) {
                if (cellArray[i][j].getCell().equals("None")) {
                    return false;
                }
            }
        }
        return true;
    }
}
