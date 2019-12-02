package com.retromania.game.mario.models.player;
/**
 * A wrapper for the information the main player needs before it can be updated
 *
 * <p>*
 */
public class MainPlayerInput {

  private int worldWidth;
  private int worldHeight;
  private int X;
  private int Y;
  private boolean hasBeenTouched;
  private boolean hasBeenHeldDown;

  public MainPlayerInput(
      int worldWidth,
      int worldHeight,
      int X,
      int Y,
      boolean hasBeenTouched,
      boolean hasBeenHeldDown) {
    this.worldWidth = worldWidth;
    this.worldHeight = worldHeight;
    this.X = X;
    this.Y = Y;
    this.hasBeenTouched = hasBeenTouched;
    this.hasBeenHeldDown = hasBeenHeldDown;
  }

  int getWorldWidth() {
    return worldWidth;
  }

  int getWorldHeight() {
    return worldHeight;
  }

  public int getX() {
    return X;
  }

  public int getY() {
    return Y;
  }

  boolean hasBeenTouched() {
    return hasBeenTouched;
  }

  boolean hasBeenHeldDown() {
    return hasBeenHeldDown;
  }
}
