package com.retromania.game.spaceship_shooter.Presenters;

import com.badlogic.gdx.Screen;
import com.retromania.game.RetroMania;
import com.retromania.game.spaceship_shooter.Models.GameStats;
import com.retromania.game.spaceship_shooter.Views.MainScreenInterface;
import com.retromania.game.spaceship_shooter.Views.PlayScreen;
import com.retromania.game.spaceship_shooter.Views.StateFactory;
import com.retromania.game.spaceship_shooter.Utils.MusicManager;
import com.retromania.game.utils.GameSaver;

/**
 * Presenter class of SpaceShipShooterStarter. It has access to models of SpaceShipShooter and
 * updates models by command of view class(SpaceShipShooterStarter)
 *
 * @author Umid, Thuy
 */
public class StarterPresenter {
  /** class that keeps all scores, best score and latest score in current session */
  private static GameStats gameStats;
  /** Current theme of game */
  private static String theme = "Independence Day";
  /** Current state of whether music isPlayed or not */
  private static boolean isMusic = false;
  /** instance of play screen */
  private Screen playScreen;
  /** instance of pause screen */
  private Screen pauseScreen;
  /** instance of menu screen */
  private Screen menuScreen;
  /** instance of setting screen */
  private Screen settingScreen;
  /** class that saves our game locally */
  private GameSaver gameSaver;

  /**
   * Constructor class for StarterPresenter
   *
   * @param mainScreen access to viewport of starter class through interface
   */
  public StarterPresenter(MainScreenInterface mainScreen) {
    gameStats = new GameStats();
    playScreen = StateFactory.getScreen("play screen", mainScreen);
    pauseScreen = StateFactory.getScreen("pause screen", mainScreen);
    menuScreen = StateFactory.getScreen("menu screen", mainScreen);
    settingScreen = StateFactory.getScreen("setting screen", mainScreen);
    gameSaver = new GameSaver("Spaceship shooter");
  }

  /** Sets screen to Pause */
  public void pause() {
    RetroMania.getRetroManiaInstance().setScreen(pauseScreen);
  }

  /** Sets screen to Play */
  public void resume() {
    RetroMania.getRetroManiaInstance().setScreen(playScreen);
  }

  /** Sets screen to Settings */
  public void modify() {
    RetroMania.getRetroManiaInstance().setScreen(settingScreen);
  }

  /**
   * Sets screen to Menu
   *
   * @param theme chosen type of theme for game
   * @param isMusic boolean that keeps whether music should play or not
   */
  public void returnMenu(String theme, boolean isMusic) {
    StarterPresenter.theme = theme;
    StarterPresenter.isMusic = isMusic;
    RetroMania.getRetroManiaInstance().setScreen(menuScreen);
  }

  /** Sets screen to Menu */
  public void returnMenu() {
    RetroMania.getRetroManiaInstance().setScreen(menuScreen);
  }

  /**
   * Creates new play screen and sets screen to new play screen
   *
   * @param mainScreen access to viewport of starter class through interface
   */
  public void restart(MainScreenInterface mainScreen) {
    playScreen = new PlayScreen(mainScreen);
    RetroMania.getRetroManiaInstance().setScreen(playScreen);
  }

  /**
   * Getter method for theme
   *
   * @return theme
   */
  public static String getTheme() {
    return theme;
  }

  /**
   * Getter method for gameStats
   *
   * @return gameStats
   */
  public static GameStats getGameStats() {
    return gameStats;
  }

  /** Updates MusicManager according to state of music */
  public static void playMusic() {
    if (isMusic) MusicManager.play();
    else MusicManager.stop();
  }

  /**
   * Saves score of user
   *
   * @param score score of user in this game
   */
  public void setScore(int score) {
    gameSaver.setScore(score);
  }
}
