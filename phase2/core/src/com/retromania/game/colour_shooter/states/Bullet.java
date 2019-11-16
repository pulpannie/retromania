package com.retromania.game.colour_shooter.states;

import java.awt.image.BufferedImage;

public class Bullet {

    int current_y;
    float end_y;

    BufferedImage image;
    public Bullet (int cur_y, float end_y_val){
        current_y = cur_y;
        end_y = end_y_val;
    }
    public void move() {
        while (current_y < end_y) {
            this.current_y += 5;
        }
    }
}
