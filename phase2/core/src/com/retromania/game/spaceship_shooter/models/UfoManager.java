package com.retromania.game.spaceship_shooter.models;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * The UFO Manager responsible for managing the list of UFOs through working with the UFO Drawer,
 * Mover, Repo actors through the UFO Manager Facade.
 *
 * @author Thuy, Umid.
 */
public class UfoManager extends Actor {
  /** The UFO Manager's Facade. */
  private UfoManagerFacade facade;

  /**
   * Initialize this UFO Manager by initialize its facade through a builder with numOfUfos UFOs.
   *
   * @param numOfUfos the numbers of UFOs that this UFO Manager manages.
   */
  public UfoManager(int numOfUfos) {
    facade =
        (new UfoManagerFacadeBuilder())
            .buildMover()
            .buildRepo(numOfUfos)
            .buildDrawer()
            .buildFacade();
  }

  /**
   * Update all the UFOs that this UFO Manager manages, and also update the rocket.
   *
   * @param rocket the rocket in the screen that is fired by the car.
   * @param hud an instance of the class that stores current game's information and result.
   */
  public void update(Rocket rocket, Hud hud) {
    facade.mover.moveUfos(facade.repo.getUfos());
    facade.mover.moveRocket(rocket, facade.repo.getUfos(), hud);
  }

  /** draw the list of UFOs that this UFO Manager manages. */
  @Override
  public void draw(Batch batch, float delta) {
    facade.drawer.drawUfos(batch, facade.repo.getUfos(), delta);
  }
}
