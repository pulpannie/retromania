package com.retromania.game.tic_tac_toe.utils;

import javax.inject.Inject;
import javax.inject.Singleton;

/** stores the currentUser's preference for the TicTacToe game. author Hyokyung Kim */
@Singleton
public class UserPreference {

  private int gameSize = 3;
  private boolean cat = false;

  @Inject
  public UserPreference() {}

  /** increases gameSize. */
  public void addSize() {
    gameSize++;
  }

  /** decreases gameSize. */
  public void decreaseSize() {
    gameSize--;
  }

  /**
   * getter for the gameSize.
   *
   * @return gameSize.
   */
  public int getGameSize() {
    return gameSize;
  }

  /**
   * sets the preference as to whether the cat wants cat icons in the game.
   *
   * @param cat whether or not the user like to have cat or normal tic tac toe.
   */
  public void setCat(boolean cat) {
    this.cat = cat;
  }

  /**
   * getter for the cat preference.
   *
   * @return cat whether or not the user like to have cat or normal tic tac toe.
   */
  public boolean getCat() {
    return cat;
  }
}
