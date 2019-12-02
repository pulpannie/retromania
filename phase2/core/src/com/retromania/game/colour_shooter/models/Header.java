package com.retromania.game.colour_shooter.models;

import com.badlogic.gdx.graphics.Color;

public class Header {
    private Integer worldTimer;
    private float timeCount;
    private int score;
    private String colourText;
    private Color colourObject;

    public Header(int totalTime) {
        worldTimer = totalTime;
        timeCount = 0;
        score = 0;
    }

    public void countDown(float dt) {
        timeCount += dt;
        if (timeCount >= 1 & worldTimer > 0) {
            worldTimer--;
            timeCount = 0;
        } else if (worldTimer <= 0) {
            boolean endGame = true;
        }
    }

    private void addScore(int addToScore) {
        if (score + addToScore >= 0) {
            score = score + addToScore;
        } else {
            score = 0;
        }
    }

    public int getCurrentScore() {
        return score;
    }

    public int getCurrentTime() {
        return worldTimer;
    }

    public Color getColourObject() {
        Color[] arrayColorObject = {
                new Color().set(255, 0, 0, 1),
                new Color().set(255, 255, 0, 1),
                new Color().set(0, 176, 240, 1),
                new Color().set(0, 176, 80, 1)
        };

        int randomNumber = (int) (Math.random() * 4);
        this.colourObject = arrayColorObject[randomNumber];
        return colourObject;
    }

    public String getCurrentColourText() {
        return colourText;
    }

    public Color getCurrentColourObject() {
        return colourObject;
    }

    public String getColorText() {
        int randomNumber1 = (int) (Math.random() * 4);
        String[] arrayColorText = {"RED", "GREEN", "BLUE", "YELLOW"};
        this.colourText = arrayColorText[randomNumber1];
        return arrayColorText[randomNumber1];
    }

    public void updateScore(float angle, String colour) {
        if (angle == 45 || angle == 135 || angle == 225 || angle == 315) {
            addScore(0);
        } else if ((315 < angle & angle < 360) || (0 < angle & angle < 45)) {
            if (colour.equalsIgnoreCase("BLUE")) {
                addScore(10);
            } else {
                addScore(-5);
            }
        } else if (45 < angle & angle < 135) {
            if (colour.equalsIgnoreCase("RED")) {
                addScore(10);
            } else {
                addScore(-5);
            }
        } else if (135 < angle & angle < 225) {
            if (colour.equalsIgnoreCase("YELLOW")) {
                addScore(10);
            } else {
                addScore(-5);
            }
        } else if (225 < angle & angle < 315) {
            if (colour.equalsIgnoreCase("GREEN")) {
                addScore(10);
            } else {
                addScore(-5);
            }
        }
    }
}
