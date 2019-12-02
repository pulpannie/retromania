package com.retromania.game.spaceship_shooter;

import com.badlogic.gdx.Screen;
import com.retromania.game.RetroMania;
import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.shared_abstractions.RetroManiaInnerGame;
import com.retromania.game.spaceship_shooter.models.GameStats;
import com.retromania.game.spaceship_shooter.presenters.StarterPresenter;
import com.retromania.game.spaceship_shooter.views.MainScreenInterface;
import com.retromania.game.spaceship_shooter.views.StateFactory;

/**
 * Starter class of spaceship shooter game
 *
 * @author Thuy, Umid
 */
public class SpaceShipShooterStarter extends RetroManiaInnerGame implements MainScreenInterface {
  /** presenter of starter */
  private StarterPresenter presenter;
  /** instance of play screen */
  private Screen playScreen;
  /** instance of pause screen */
  private Screen pauseScreen;
  /** instance of menu screen */
  private Screen menuScreen;
  /** instance of setting screen */
  private Screen settingScreen;

  /** Constructor method for SpaceShipShooterStarter */
  public SpaceShipShooterStarter() {
    super("Spaceship shooter", RetroManiaGame.Orientation.VERTICAL);
    presenter = new StarterPresenter(this);
    playScreen = StateFactory.getScreen("play screen", this);
    pauseScreen = StateFactory.getScreen("pause screen", this);
    menuScreen = StateFactory.getScreen("menu screen", this);
    settingScreen = StateFactory.getScreen("setting screen", this);
  }

  /** handles input */
  @Override
  public void handleInput() {}

  /** shows when screen set to SpaceShipsShooterStarter, calls presenter to set screen menu */
  @Override
  public void show() {
    RetroMania.getRetroManiaInstance().setScreen(menuScreen);
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
    RetroMania.getRetroManiaInstance().setScreen(pauseScreen);
  }

  /** Request presenter to set screen to Play */
  @Override
  public void resume() {
    RetroMania.getRetroManiaInstance().setScreen(playScreen);
  }

  /** Request presenter to set screen to Settings */
  @Override
  public void modify() {
    RetroMania.getRetroManiaInstance().setScreen(settingScreen);
  }

  /**
   * Request presenter to set screen to Menu
   *
   * @param theme chosen type of theme for game
   * @param isMusic boolean that keeps whether music should play or not
   */
  @Override
  public void returnMenu(String theme, boolean isMusic) {
    RetroMania.getRetroManiaInstance().setScreen(menuScreen);
    StarterPresenter.setTheme(theme);
    StarterPresenter.setIsMusic(isMusic);
  }

  /** Request presenter to set screen to Menu */
  public void returnMenu() {
    RetroMania.getRetroManiaInstance().setScreen(menuScreen);
  }

  public void restart() {
    playScreen = StateFactory.getScreen("play screen", this);
    RetroMania.getRetroManiaInstance().setScreen(playScreen);
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
