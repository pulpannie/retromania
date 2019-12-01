package com.retromania.game.spaceship_shooter.individuals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
/**
 * Our player/car class that we are using to shoot UFOs and moving left/right direction
 *
 * @author  Umid, Thuy
 *
 * */
public class Car extends Actor {
    /**
     * x coordinate of Car
     */
    private int x;

    /**
     * y coordinate of Car
     */
    private int y;

    /**
     * width of Car
     */
    private int width;

    /**
     * height of Car
     */
    private int height;

    /**
     * integer that stores turning side of car
     */
    private int rev = 1;

    /**
     * boolean stores whether car turned right
     */
    private boolean goingRight = true;

    /**
     * Car's GUI.
     */
    private Texture texture;

    /**
     * getter for iRocket
     * */
    public Rocket getRocket() {
        return rocket;
    }

    /**
     * iRocket: rocket that can be used to shoot UFOs
     */
    private Rocket rocket = null;


    /**
     * Initializer for Car
     * */
    public Car(){
        width = Gdx.graphics.getWidth()/3;
        height= Gdx.graphics.getHeight()/8;
        y = 320;
        x = Gdx.graphics.getWidth()/2;
        texture = new Texture("spaceship_shooter/car_try.png");
    }

    /**
     * change direction of Car
     * */
    private void turnSide(){
        rev *= -1;
        goingRight = !goingRight;
    }


    /**
     * draw the Car.
     * @param batch
     * @param parentAlpha
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, x-rev*width/2, y-height/2, rev*width, height);
    }

    /**
     * Move the Car to the right
     * */
    public void moveRight(){
        if (x + width/2 <= Gdx.graphics.getWidth())
            x += 15;

        if (!goingRight)
            turnSide();

    }
    /**
     * Move the Car to the left
     * */
    public void moveLeft(){
        if (x - width/2 >= 0)
            x -= 15;
        if (goingRight)
            turnSide();
    }
    /**
     * Create/shoot the rocket
     * */
    public void shoot(){
        if ((rocket == null) || (rocket.reach_top())){
            this.rocket = new Rocket(x, y);
        }
    }
    /**
     * Check whether rocket is shot
     * @return true if the rocket is shot, false if vice versa.
     */
    public boolean shot(){
        return rocket != null && !rocket.reach_top();
    }


}
