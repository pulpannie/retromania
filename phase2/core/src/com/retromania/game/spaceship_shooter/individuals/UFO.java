package com.retromania.game.spaceship_shooter.individuals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;

/**
 * The flying UFO displayed on the screen.
 * @author Thuy, Umid.
 */
public class UFO {
    /**
     * The UFO's location in (x,y) format.
     */
    private int x;
    private int y;

    /**
     * The UFO's width.
     */
    private int width;

    /**
     * The UFO's height.
     */
    private int height;

    /**
     * The UFO's GUI.
     */
    static Texture texture = new Texture("spaceship_shooter/ufo.png");


    /**
     * Initialize this UFO with (x,y) location.
     *
     * @param x    the x coordinate.
     * @param y    the y coordinate.
     */
    public UFO(int x, int y){
        this.x = x - Gdx.graphics.getWidth()/8;
        this.y = y;
        width = Gdx.graphics.getWidth()/8;
        height = Gdx.graphics.getHeight()/20;

    }


    /**
     * move this UFO right on the screen.
     */
    public void moveRight(){
        if (this.x + width/2 >= Gdx.graphics.getWidth())
            recreate();

        this.x += 20;
    }


    /**
     * Draw this UFO.
     *
     * @param batch
     * @param parentAlpha
     */
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, x-width/2, y-height/2, width, height);
    }


    /**
     * Check if the rocket fired by the car hit this UFO.
     *
     * @param rocket the rocket fired by the car.
     * @return true if the rocket hit this UFO, false if it not.
     */
    public boolean isRocketTouches(Rocket rocket){
        double d;
        double dp = Math.sqrt(Math.pow(x-rocket.getX(),2)+ Math.pow(y-rocket.getY(),2));
        if (x -width/2 < rocket.getX()&& rocket.getX() < x+ width/2)
            d = height/2 + rocket.getHeight()/2;
        else
            d = width/2 + rocket.getWidth()/2;
        return d >= dp;
    }

    /**
     * recreate this UFO in a different (x,y) coordinate.
     */
    public void recreate(){
        x =(int) (Math.random() * -80);
        y = (int) (Math.random() * (Gdx.graphics.getHeight()/2 + 1) + Gdx.graphics.getHeight()/4);
    }
}
