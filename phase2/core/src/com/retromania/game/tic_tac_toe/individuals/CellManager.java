package com.retromania.game.tic_tac_toe.individuals;

import com.retromania.game.tic_tac_toe.utils.UserPreference;

public class CellManager {
  Cell[][] cellArray;
  String winner = "None";
  UserPreference userPreference;

  public CellManager(UserPreference userPreference) {
    this.userPreference = userPreference;
    show();
  }

  public void show() {
    cellArray = new Cell[userPreference.getGameSize()][userPreference.getGameSize()];
    for (int i = 0; i < userPreference.getGameSize(); i++) {
      for (int j = 0; j < userPreference.getGameSize(); j++) {
        cellArray[i][j] =
            new Cell();
      }
    }
  }

  public void touchCell(int i, int j, String currentTurn){
    cellArray[i][j].setIsTouched(true);
    cellArray[i][j].setCell(currentTurn);
  }

  public boolean isCellTouched(int i, int j){
    return cellArray[i][j].getIsTouched();
  }

  public String getCellState(int i, int j){
    return cellArray[i][j].getCell();
  }

  public boolean checkColumn(int n) {
    for (int i = 0; i < userPreference.getGameSize(); i++) {
      int tmp = 0;
      for (int j = 0; j < userPreference.getGameSize() - 1; j++) {
        if (cellArray[i][j].equal(cellArray[i][j + 1])) {
          tmp++;
          if (tmp == n - 1) {
            winner = cellArray[i][j].getCell();
            return true;
          }
        }
      }
    }
    return false;
  }

  public boolean checkRow(int n) {
    for (int i = 0; i < userPreference.getGameSize(); i++) {
      int tmp = 0;
      for (int j = 0; j < userPreference.getGameSize() - 1; j++) {
        if (cellArray[j][i].equal(cellArray[j + 1][i])) {
          tmp++;
          if (tmp == n - 1) {
            winner = cellArray[j][i].getCell();
            return true;
          }
        }
      }
    }
    return false;
  }

  public boolean checkDiagLeft(int n) {
    int tmp = 0;
    for (int i = 0; i < userPreference.getGameSize() - 1; i++) {
      if (cellArray[i][i].equal(cellArray[i + 1][i + 1])) {
        tmp++;
        if (tmp == n - 1) {
          winner = cellArray[i][i].getCell();
          return true;
        }
      }
    }
    return false;
  }

  public boolean checkDiagRight(int n) {
    int tmp = 0;
    for (int i = 0; i < userPreference.getGameSize() - 3; i++) {
      for (int j = 0; j < userPreference.getGameSize() -2; j++){
        if (cellArray[j][userPreference.getGameSize() - i - 1].equal(
            cellArray[j + 1][userPreference.getGameSize() - i - 2]) && (
                cellArray[j + 1][userPreference.getGameSize() - i - 2]).equals(cellArray[j+2][userPreference.getGameSize()-i-3])) {
          tmp++;
          if (tmp == n - 1) {
            winner = cellArray[i][userPreference.getGameSize() - i - 1].getCell();
            return true;
          }
        }
      }
    }
    return false;
  }

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
