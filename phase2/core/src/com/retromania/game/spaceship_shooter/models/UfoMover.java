package com.retromania.game.spaceship_shooter.models;

import java.util.List;

/**
 * The UFO mover responsible for moving a list of UFOs.
 *
 * @author Thuy, Umid.
 */
class UfoMover {

  /**
   * Update/move the list of given UFOs ufos.
   *
   * @param ufos the given list of UFOs.
   */
  void moveUfos(List<UFO> ufos) {
    for (UFO i : ufos) i.moveRight();
  }

  /**
   * Update/move the rocket on the screen and check if the rocket hit any UFOs in the given list of
   * UFOs ufos, and update the score and that UFO accordingly.
   *
   * @param rocket the rocket displayed on the screen.
   * @param ufos the given list of UFOs.
   * @param hud the instance of a class that stores the current game's information and result.
   */
  void moveRocket(Rocket rocket, List<UFO> ufos, Hud hud) {

    if (rocket != null && !rocket.reach_top()) {
      rocket.moveUp();
      for (UFO i : ufos)
        if (i.isRocketTouches(rocket)) {
          hud.addScore(10);
          i.recreate();
        }
    }
  }
}
