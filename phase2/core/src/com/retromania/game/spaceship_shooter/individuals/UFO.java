package com.retromania.game.spaceship_shooter.individuals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.retromania.game.spaceship_shooter.SpaceShipShooterStarter;

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
     * The UFO's GUI with independence day theme.
     */
    private Texture texture1 = new Texture("spaceship_shooter/ufo.png");

    /**
     * The UFO's GUI with halloween theme.
     */
    private Texture texture2 = new Texture("spaceship_shooter/halloween.png");

    /**
     * The UFO's GUI with christmas theme.
     */
    private Texture texture3 = new Texture("spaceship_shooter/christmas.png");


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
    void moveRight(){
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
        if (SpaceShipShooterStarter.getTheme().equalsIgnoreCase("independence day"))
            batch.draw(texture1, x-width/2, y-height/2, width, height);
        else if (SpaceShipShooterStarter.getTheme().equalsIgnoreCase("halloween"))
            batch.draw(texture2, x-width/2, y-height/2, width, height);
        else
            batch.draw(texture3, x-width/2, y-height/2, width, height);
    }


    /**
     * Check if the rocket fired by the car hit this UFO.
     *
     * @param rocket the rocket fired by the car.
     * @return true if the rocket hit this UFO, false if it not.
     */
    boolean isRocketTouches(Rocket rocket){
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
    void recreate(){
        x =(int) (Math.random() * -80);
        y = (int) (Math.random() * (Gdx.graphics.getHeight()/2 + 1) + Gdx.graphics.getHeight()/4);
    }
}
