package com.retromania.game.tic_tac_toe.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

/**
 * builds instances of ImageButton class.
 *
 * @author Hyokyung Kim.
 */
class ImageButtonBuilder {
  private ImageButton imageButton;

  /**
   * @param path the name of the file
   * @return the constructed ImageButton.
   */
  ImageButton buildButton(String path) {
    path = "tic_tac_toe/" + path;
    imageButton =
        new ImageButton(
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(path)))));
    return imageButton;
  }

  /**
   * @param path1 the name of the first file.
   * @param path2 the name of the second file.
   * @return the constructed ImageButton.
   */
  ImageButton buildOnOffButton(String path1, String path2) {
    path1 = "tic_tac_toe/" + path1;
    path2 = "tic_tac_toe/" + path2;
    imageButton =
        new ImageButton(
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(path1)))),
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(path2)))),
            new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal(path2)))));
    return imageButton;
  }
}
