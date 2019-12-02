package com.retromania.game.mario.models.player;

/**
 * A wrapper for the information given about the main player in collisions
 *
 * <p>*
 */
public class MainPlayerCollisionInfo {
  private MainPlayer mainPlayer;
  private BodyPart bodyPart;

  public MainPlayerCollisionInfo(MainPlayer mainPlayer, BodyPart bodyPart) {
    this.mainPlayer = mainPlayer;
    this.bodyPart = bodyPart;
  }

  public BodyPart getBodyPart() {
    return bodyPart;
  }

  public MainPlayer getMainPlayer() {
    return mainPlayer;
  }
}
