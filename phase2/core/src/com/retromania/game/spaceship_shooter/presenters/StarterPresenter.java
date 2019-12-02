package com.retromania.game.spaceship_shooter.presenters;

import com.retromania.game.spaceship_shooter.models.GameStats;
import com.retromania.game.spaceship_shooter.views.MainScreenInterface;
import com.retromania.game.spaceship_shooter.utils.MusicManager;
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
  /** class that saves our game locally */
  private GameSaver gameSaver;

  /**
   * Constructor class for StarterPresenter
   *
   * @param mainScreen access to viewport of starter class through interface
   */
  public StarterPresenter(MainScreenInterface mainScreen) {
    gameStats = new GameStats();
    gameSaver = new GameSaver("Spaceship shooter");
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

  public static void setTheme(String theme) {
    StarterPresenter.theme = theme;
  }

  public static void setIsMusic(boolean isMusic) {
    StarterPresenter.isMusic = isMusic;
  }
}
