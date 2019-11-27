package com.retromania.game.spaceship_shooter.individuals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
/**
 * Our player/car class that we are using to shoot UFOs and moving left/right direction
 *
 * @author  Umid, Thuy
 *
 * */
public class Car extends Actor {
    /**
     * Variables:
     * x: x coordinate of Car
     * y: y coordinate of Car
     * width: width of Car
     * height: height of Car
     * rev: integer that stores turning side of car
     * goingRight: boolean stores whether car turned right
     * texture: gui of Car
     * iRocket: rocket that can be used to shoot UFOs
     * */
    private ShapeRenderer shapeRenderer;
    static private boolean projectionMatrixSet;
    private int x;
    private int y;
    private int width;
    private int height;
    private int rev = 1;
    public boolean goingRight = true;
    private Texture texture;

    /**
     * getter for iRocket
     * */
    public Rocket getiRocket() {
        return iRocket;
    }

    private Rocket iRocket = null;


    /**
     * Initializer for Car
     * */
    public Car(){
        shapeRenderer = new ShapeRenderer();
        projectionMatrixSet = false;
        width = Gdx.graphics.getWidth()/3;
        height= Gdx.graphics.getHeight()/8;
        y = 320;
        x = Gdx.graphics.getWidth()/2;
        texture = new Texture("spaceship_shooter/car_try.png");
    }

    /**
     * Method that changes direction of Car
     * */
    public void turnSide(){
        rev *= -1;
        goingRight = !goingRight;
    }

    /**
     * Method that draws Car
     * */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, x-rev*width/2, y-height/2, rev*width, height);
    }


    /**
     * @deprecated we don't use this method anymore
     * */
    public void draw_2(Batch batch, float parentAlpha) {
        batch.end();
        if(!projectionMatrixSet){
            shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
            projectionMatrixSet = true;
        }
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        //body of car
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.rect(x-rev*width/2, y-height/2, rev*width, height);
        // "head of car"
        shapeRenderer.rect(x-rev*(width-40)/2, y+height/2, rev*width/4, height);
        // "window" of car
        shapeRenderer.setColor(Color.BLUE);
        shapeRenderer.rect(x-rev*(width-60)/2, y+height/2, rev*(width-60)/4, height-20);
        //left wheel of car
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.circle(x-rev*(width-120)/2, y-height/2, 50);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(x-rev*(width-120)/2, y-height/2, 20);
        //right wheel of car
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.circle(x+rev*(width-120)/2, y-height/2, 50);
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.circle(x+rev*(width-120)/2, y-height/2, 20);
        //"rocket handler"
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(x-rev*(width-80)/4, y+height/2, rev*(3*width-100)/4, height);
        shapeRenderer.rect(x-rev*(width)/8, y+height, rev*width/4, height);
        shapeRenderer.end();


        batch.begin();
    }

    /**
     * Method that mcves car to right
     * */
    public void moveRight(){
        if (x + width/2 <= Gdx.graphics.getWidth())
            x += 15;

    }
    /**
     * Method that mcves car to left
     * */
    public void moveLeft(){
        if (x - width/2 >= 0)
            x -= 15;
    }
    /**
     * Method that creates/shoots rocket
     * */
    public void shoot(){
        if ((iRocket == null) || (iRocket.reach_top())){
            this.iRocket = new Rocket(x, y);
        }
    }
    /**
     * Checks whether rocket is shooted
     */
    public boolean shooted(){
        return iRocket != null && !iRocket.reach_top();
    }


}
