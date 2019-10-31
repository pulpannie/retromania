package com.retromania.game.spaceship_shooter.individuals;

import java.util.ArrayList;
import java.util.List;

public class GameStats {
    private List<Integer> scoreboard;

    public GameStats() {
        scoreboard = new ArrayList<Integer>();
    }

    public int getHighScore() {
        return highScore;
    }

    private int highScore = 0;
    private int timePlay = 0;

    public void update(int score) {
        scoreboard.add(score);
        timePlay++;
        highScore = (score > highScore) ? score : highScore;
    }

    public int getLatestScore() {
        return (timePlay == 0) ? 0 : scoreboard.get(timePlay-1);
    }
}
