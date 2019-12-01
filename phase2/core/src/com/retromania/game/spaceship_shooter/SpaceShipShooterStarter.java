package com.retromania.game.spaceship_shooter;

import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.shared_abstractions.RetroManiaInnerGame;
import com.retromania.game.spaceship_shooter.Models.GameStats;
import com.retromania.game.spaceship_shooter.Presenters.StarterPresenter;
import com.retromania.game.spaceship_shooter.Views.MainScreenInterface;

/**
 * Starter class of spaceship shooter game
 *
 * @author Umid, Thuy
 */
public class SpaceShipShooterStarter extends RetroManiaInnerGame implements MainScreenInterface {
  /** presenter of starter */
  private StarterPresenter presenter;

  /** Constructor method for SpaceShipShooterStarter */
  public SpaceShipShooterStarter() {
    super("Spaceship shooter", RetroManiaGame.Orientation.VERTICAL);
    presenter = new StarterPresenter(this);
  }

  /** handles input */
  @Override
  public void handleInput() {}

  /** shows when screen set to SpaceShipsShooterStarter, calls presenter to set screen menu */
  @Override
  public void show() {
    presenter.returnMenu();
  }

  /**
   * Resizes screen
   *
   * @param width width of new screen
   * @param height height of new screen
   */
  @Override
  public void resize(int width, int height) {}

  /** Request presenter to set screen to Pause */
  @Override
  public void pause() {
    presenter.pause();
  }

  /** Request presenter to set screen to Play */
  @Override
  public void resume() {
    presenter.resume();
  }

  /** Request presenter to set screen to Settings */
  @Override
  public void modify() {
    presenter.modify();
  }

  /**
   * Request presenter to set screen to Menu
   *
   * @param theme chosen type of theme for game
   * @param isMusic boolean that keeps whether music should play or not
   */
  @Override
  public void returnMenu(String theme, boolean isMusic) {
    presenter.returnMenu(theme, isMusic);
  }

  /** Request presenter to set screen to Menu */
  public void returnMenu() {
    presenter.returnMenu();
  }

  public void restart() {
    presenter.restart(this);
  }

  /** Request presenter to play music */
  public static void playMusic() {
    StarterPresenter.playMusic();
  }

  /**
   * Request presenter to get theme of game
   *
   * @return theme of game
   */
  public static String getTheme() {
    return StarterPresenter.getTheme();
  }

  /**
   * Request presenter to get GameStats
   *
   * @return game statistics
   */
  public static GameStats getGameStats() {
    return StarterPresenter.getGameStats();
  }

  /**
   * Request presenter to save score of current user
   *
   * @param score the score of the current user
   */
  public void saveScore(int score) {
    presenter.setScore(score);
  }
}
