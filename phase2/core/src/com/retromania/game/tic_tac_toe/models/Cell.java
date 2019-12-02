package com.retromania.game.tic_tac_toe.models;

/**
 * Model represents one Cell on the TicTacToe board.
 *
 * @author Hyokyung Kim
 */
public class Cell {
  private boolean isTouched;
  private String state;

  /** constructor makes empty Cell. */
  public Cell() {
    isTouched = false;
    state = "None";
  }

  /**
   * setter that changes the state of the Cell.
   *
   * @param state the Cell will be changed to.
   */
  void setCell(String state) {
    this.state = state;
  }

  /**
   * getter of the state of the Cell.
   *
   * @return state the Cell is in.
   */
  String getCell() {
    return this.state;
  }

  /**
   * getter of the isTouched variable.
   *
   * @return whether the Cell has been touched.
   */
  boolean getIsTouched() {
    return isTouched;
  }

  /**
   * setter of the isTouched variable
   *
   * @param bool value of what isTouched is going to be.
   */
  void setIsTouched(boolean bool) {
    isTouched = bool;
  }

  /**
   * checks if the Cell is equal to another Cell.
   *
   * @param other the other Cell being compared to.
   * @return if the two Cells are equal.
   */
  boolean equal(Cell other) {
    if (!this.getCell().equals("None")) {
      return this.getCell().equals(other.getCell());
    }
    return false;
  }
}
