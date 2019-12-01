package com.retromania.game.spaceship_shooter.Models;

/**
 * The UFO Manager Facade responsible for managing the list of UFOs through working with the UFO
 * Drawer, Mover, Repo.
 *
 * @author Thuy, Umid.
 */
public class UfoManagerFacade {
  /** The UFO drawer responsible for drawing the list of UFOs. */
  UfoDrawer drawer;

  /** The UFO mover responsible for moving the list of UFOs. */
  UfoMover mover;

  /** The UFO repo responsible for storing the list of UFOs. */
  UfoRepo repo;

  /**
   * Initialize this UFO Manager Facade with the Ufo Drawer, Mover and Repo.
   *
   * @param drawer the given UFO's drawer.
   * @param mover the given UFO's mover.
   * @param repo the given UFO's repo.
   */
  public UfoManagerFacade(UfoDrawer drawer, UfoMover mover, UfoRepo repo) {
    this.drawer = drawer;
    this.mover = mover;
    this.repo = repo;
  }
}
