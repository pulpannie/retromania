package com.retromania.game.shared_abstractions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.retromania.game.RetroMania;

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
  public RetroManiaInnerGame(String name, RetroManiaGame.Orientation orientation){
    this.name = name;
    this.orientation = orientation;
    setBestUser();
  }
  //  TODO this method should be DEPRECATED and all references to it should be changed
  /**
   * THIS METHOD IS DEPRECATED, PLEASE USE THE STATIC FUNCTION TO GET INSTANCE OF THE
   * RETROMANIA GAME IN THE FUTURE.
   */
  public RetroManiaInnerGame(
      RetroManiaGame game, String name, RetroManiaGame.Orientation orientation) {
    super(game);
    this.name = name;
    this.orientation = orientation;
    setBestUser();
    System.out.println("THIS METHOD IS DEPRECATED, PLEASE USE THE STATIC FUNCTION " +
            "TO GET INSTANCE OF THE RETROMANIA GAME IN THE FUTURE.");
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
