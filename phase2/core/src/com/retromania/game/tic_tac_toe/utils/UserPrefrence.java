package com.retromania.game.tic_tac_toe.utils;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UserPrefrence {

  private int gameSize = 3;
  private boolean cat = false;

  @Inject
  public UserPrefrence() {}

  public void addSize() {
    gameSize++;
  }

  public void decreaseSize() {
    gameSize--;
  }

  public int getGameSize() {
    return gameSize;
  }

  public void setCat(boolean cat) {
    this.cat = cat;
  }

  public boolean getCat() {
    return cat;
  }
}
