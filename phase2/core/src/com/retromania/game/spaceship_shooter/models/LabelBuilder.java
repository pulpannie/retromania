package com.retromania.game.spaceship_shooter.models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/** Builder class of Label */
public class LabelBuilder {
  /** string you want to show up in GUI */
  private CharSequence text;

  /** font of text(including size) */
  private BitmapFont bitmapFont;

  /** color of text */
  private Color color;

  /** display style of text */
  private Label.LabelStyle labelStyle;

  /**
   * Build text
   *
   * @param text the text we need to build
   * @return instance of builder class to finish building label in 1 line
   */
  public LabelBuilder buildText(CharSequence text) {
    this.text = text;
    return this;
  }
  /**
   * Build the font of the text
   *
   * @param size the size of the font of the text that we will set.
   * @return instance of builder class to finish building label in 1 line
   */
  public LabelBuilder buildFont(float size) {
    bitmapFont = new BitmapFont();
    bitmapFont.getData().setScale(size);

    return this;
  }
  /**
   * Build the color of the text.
   *
   * @return instance of builder class to finish building label in 1 line
   */
  public LabelBuilder buildColor() {
    color = Color.WHITE;
    return this;
  }
  /**
   * Build labelStyle of text with given font and color
   *
   * @return instance of builder class to finish building label in 1 line
   */
  public LabelBuilder buildLabelStyle() {
    labelStyle = new Label.LabelStyle(bitmapFont, color);
    return this;
  }
  /**
   * Method that builds Label with given builder parameters
   *
   * @return label which is final product
   */
  public Label buildLabel() {
    return new Label(text, labelStyle);
  }
}
