package com.retromania.game.spaceship_shooter.individuals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Rocket is the entity we use to shoot rockets. It can only move vertically for now.
 *
 * @author Umid, Thuy
 */

public class Rocket extends Actor {
    /**
     * Variables:
     * x: integer that stores x coordinate of Rocket
     * y: integer that stores y coordinate of Rocket
     * width: width of rocket
     * height: height of rocket
     * texture: gui of rocket
     * */
    private ShapeRenderer shapeRenderer;
    static private boolean projectionMatrixSet;

    private int x;
    private int y;

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }

    private int width;
    private int height;
    private Texture texture = new Texture("spaceship_shooter/rocket_fire.png");


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
        shapeRenderer = new ShapeRenderer();
        projectionMatrixSet = false;
    }

    //getter of x coordinate
    @Override
    public float getX() {
        return x;
    }
    //getter of y coordinate
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
     * Method that makes rocket go up
     * */
    void moveUp(){
        this.y += 50;
    }

    /**
     * Draws the entity by batch
     *
     * */
    public void draw(Batch batch, float parentAlpha){
        batch.draw(texture, x-width/2, y-height/2, width, height);
    }
}
