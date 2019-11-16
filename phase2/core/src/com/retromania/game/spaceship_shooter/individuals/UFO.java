package com.retromania.game.spaceship_shooter.individuals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

public class UFO {
    private int x;
    private int y;
    private int width;
    private int height;
    static Texture texture = new Texture("ufo.png");

    public UFO(int x, int y){
        this.x = x - Gdx.graphics.getWidth()/8;
        this.y = y;
        width = Gdx.graphics.getWidth()/8;
        height = Gdx.graphics.getHeight()/20;

    }

    public void moveRight(){
        if (this.x + width/2 >= Gdx.graphics.getWidth())
            recreate();

        this.x += 20;
    }



    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, x-width/2, y-height/2, width, height);
    }


    public boolean isRocketTouches(Rocket rocket){
        double d;
        double dp = Math.sqrt(Math.pow(x-rocket.getX(),2)+ Math.pow(y-rocket.getY(),2));
        if (x -width/2 < rocket.getX()&& rocket.getX() < x+ width/2)
            d = height/2 + rocket.getHeight()/2;
        else
            d = width/2 + rocket.getWidth()/2;
        return d >= dp;
    }

    public void recreate(){
        x =(int) (Math.random() * -80);
        y = (int) (Math.random() * (Gdx.graphics.getHeight()/2 + 1) + Gdx.graphics.getHeight()/4);
    }
}
