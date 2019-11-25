package com.retromania.game.colour_shooter.individuals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.ArrayList;

public class Bullets {
    private Stage stage;
    private ArrayList<Texture> bullet_array;

    public Bullets(Stage stage){
//        Tank tank = new Tank(mainscreen.getTankPreference());
//        Image tank_img = new Image(tank.getTankTexture());
//        tank_img.setSize((float) (width * 0.3), (float) (height * 0.2));
//        tank_img.setPosition((float)(width / 2) - (tank_img.getWidth() / 2), 0);
//        stage.addActor(tank_img);
    }

    private void add() {
        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();
        Image bullet_img = new Image(new Texture(Gdx.files.internal("colour_shooter/square.png")));
        bullet_img.setSize((float) (width * 0.3), (float) (height * 0.2));
        bullet_img.setPosition((float)(width / 2) - (bullet_img.getWidth() / 2), 0);
        stage.addActor(bullet_img);
    }
}
