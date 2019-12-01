package com.retromania.game.spaceship_shooter.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
/**
 * Builder class for ImageButtons, just requires path of texture starting from "spaceship_shooter/"
 *
 * @author Umid, Thuy
 */
public class ImageButtonBuilder {
  /** texture of image */
  private Texture texture;

  /**
   * Method that builds texture of image
   *
   * @param path the inner path of the texture inside the spaceship_shooter package.
   * @return instance of class so that builder can be one-line
   */
  public ImageButtonBuilder buildTexture(String path) {
    path = "spaceship_shooter/" + path;
    texture = new Texture(path);
    return this;
  }

  /**
   * Build button
   *
   * @return ImageButton which is final product of builder
   */
  public ImageButton buildButton() {
    return new ImageButton(new TextureRegionDrawable(new TextureRegion(texture)));
  }
}
