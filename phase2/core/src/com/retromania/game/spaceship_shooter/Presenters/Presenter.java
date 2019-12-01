package com.retromania.game.spaceship_shooter.Presenters;

import com.retromania.game.spaceship_shooter.SpaceShipShooterStarter;
import com.retromania.game.spaceship_shooter.Models.Background;

/** Parent class for most of presenter classes. It updates background
 *
 * @author Umid, Thuy
 * */
public class Presenter {
  /** instance that keeps our background/model */
  private Background background;

  /** Constructor class for presenter */
  public Presenter() {
    background = new Background();
  }

  /**
   * Getter function for background
   *
   * @return background
   */
  public Background getBackground() {
    return background;
  }

  /** updates music class */
  public void update(float dt) {
    SpaceShipShooterStarter.playMusic();
  }
}
