package com.retromania.game.shared_abstractions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

import java.util.List;

public abstract class RetroManiaInnerGame extends RetroManiaScreen {
  private RetroManiaGame.Orientation orientation;
  private String name;

  Preferences preferences;
  void makeSaveFile(String name){
    preferences =  Gdx.app.getPreferences(name);
  }

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
  }

  public abstract void save(Object... args);

  public abstract List<Object> retrieve();
}
