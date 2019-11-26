package com.retromania.game.spaceship_shooter.individuals;

import java.util.ArrayList;
import java.util.List;
/**
 * Class that controls game statistics including best and last score
 * */
public class GameStats {
    /**
     * scoreboard: keep track of all games played
     * highScore: integer that scores highest score
     * timePlay: integer that keeps track of number of times games played
     * */
    private List<Integer> scoreboard;

    /**
     * Initializer of GameStats class
     * */
    public GameStats() {
        scoreboard = new ArrayList<Integer>();
    }
    /**
     * getter for high score
     * @return highScore
     * */
    public int getHighScore() {
        return highScore;
    }

    private int highScore = 0;
    private int timePlay = 0;
    /**
     * Method that add new score to scoreboard and checks if it is the new high score
     * */
    public void update(int score) {
        scoreboard.add(score);
        timePlay++;
        highScore = (score > highScore) ? score : highScore;
    }
    /**
     * Getter for latestScore
     * @return latestScore
     * */
    public int getLatestScore() {
        return (timePlay == 0) ? 0 : scoreboard.get(timePlay-1);
    }
}
