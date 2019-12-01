package com.retromania.game.spaceship_shooter.models;

import java.util.ArrayList;
import java.util.List;
/** Class that controls game statistics including best and last score */
public class GameStats {

  /** keep track of all games played */
  private List<Integer> scoreboard;

  /** integer that scores highest score */
  private int highScore = 0;

  /** integer that keeps track of number of times games played */
  private int timePlay = 0;

  /** Initializer of GameStats class */
  public GameStats() {
    scoreboard = new ArrayList<>();
  }
  /**
   * getter for high score
   *
   * @return highScore
   */
  public int getHighScore() {
    return highScore;
  }

  /**
   * Method that add new score to scoreboard and checks if it is the new high score
   *
   * @param score: the score of the user.
   */
  public void update(int score) {
    scoreboard.add(score);
    timePlay++;
    highScore = (score > highScore) ? score : highScore;
  }
  /**
   * Getter for latestScore
   *
   * @return the latestScore
   */
  public int getLatestScore() {
    return (timePlay == 0) ? 0 : scoreboard.get(timePlay - 1);
  }
}
