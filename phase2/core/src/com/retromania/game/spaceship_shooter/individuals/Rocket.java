package com.retromania.game.spaceship_shooter.individuals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Rocket is the entity we use to shoot rockets. It can only move vertically for now.
 *
 * @author Umid, Thuy
 */

public class Rocket extends Actor {
    /**
     * Rocket's GUI.
     */
    private Texture texture = new Texture("spaceship_shooter/rocket_fire.png");

    /**
     * integer that stores x coordinate of Rocket
     */
    private int x;

    /**
     * integer that stores y coordinate of Rocket
     */
    private int y;

    /**
     * width of rocket
     */
    private int width;

    /**
     * height of rocket
     */
    private int height;

    /**
     * Getter method for rocket's width.
     * @return the rocket's width.
     */
    @Override
    public float getWidth() {
        return width;
    }

    /**
     * Getter method for rocket's height.
     * @return the rocket's height.
     */
    @Override
    public float getHeight() {
        return height;
    }

    /**
     * Constructor of Rocket class
     *
     * @param x coordinate 'x' of ufo
     * @param y coordinate 'y' of ufo
     * */
    public Rocket(int x, int y){
        width = Gdx.graphics.getWidth()/10;
        height = Gdx.graphics.getHeight()/16;
        this.x = x;
        this.y = y;
    }

    /**
     * Getter method for rocket's x coordinate.
     * @return the rocket's x coordinate.
     */
    @Override
    public float getX() {
        return x;
    }

    /**
     * Getter method for rocket's x coordinate.
     * @return the rocket's x coordinate.
     */
    @Override
    public float getY() {
        return y;
    }

    /**
     * Checks if rocket touches top
     *
     * @return true if and only if rocket touches ceil
     * */
    boolean reach_top(){
        return y+ height >= Gdx.graphics.getHeight();
    }

    /**
     * Make the rocket go up
     * */
    void moveUp(){
        this.y += 50;
    }

    /**
     * Draws the entity by batch
     *
     * @param batch
     * @param parentAlpha
     * */
    public void draw(Batch batch, float parentAlpha){
        batch.draw(texture, x-width/2, y-height/2, width, height);
    }
}
