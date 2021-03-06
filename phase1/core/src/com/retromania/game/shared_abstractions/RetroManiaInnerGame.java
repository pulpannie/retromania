package com.retromania.game.shared_abstractions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.List;

public abstract class RetroManiaInnerGame extends RetroManiaScreen {
  private RetroManiaGame.Orientation orientation;
  private String name;
  protected User currentUser;
  protected User bestUser;




  public RetroManiaGame.Orientation getOrientation() {
    return orientation;
  }

  public String getName() {
    return name;
  }

  public RetroManiaInnerGame(
      RetroManiaGame game, String name, RetroManiaGame.Orientation orientation) {
    super(game);
    this.name = name;
    this.orientation = orientation;
    setBestUser();
  }
  /**
   *
   * @param name a user name with the length less than or equal to 3, if We are to make a general User
   *
   * **/
  public abstract void setCurrentUser(String name);

  public abstract void setBestUser();

  public String getBestUserName() {
    return bestUser.getUserName();
  }

  abstract public Integer getBestUserScore();

  public abstract void save(Object... args);

  public abstract List<Object> retrieve();
}
