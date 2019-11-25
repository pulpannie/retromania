package com.retromania.game.colour_shooter.individuals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Background {
    public Texture back_texture;

    public Background (String screen_name) {
        if (screen_name.equals("play_screen")) {
            back_texture = new Texture(Gdx.files.internal("colour_shooter/gray_bg.png"));
        }
        else {
            back_texture = new Texture("gray_bg.png");
        }
    }
}
