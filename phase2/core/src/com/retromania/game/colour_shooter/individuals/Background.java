package com.retromania.game.colour_shooter.individuals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * This class is responsible for forming the backgrounds of the game based on the screen name passed
 * in.
 */
public class Background {
  private Texture back_texture;

  public Background(String screen_name) {
    if (screen_name.equals("play_screen")) {
      back_texture = new Texture(Gdx.files.internal("colour_shooter/gray_bg.png"));
    } else {
      back_texture = new Texture(Gdx.files.internal("colour_shooter/purple_bg.jpg"));
    }
  }

  public Texture getBackgroundTexture() {
    return back_texture;
  }
}
