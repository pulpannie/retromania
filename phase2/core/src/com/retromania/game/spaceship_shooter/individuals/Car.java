package com.retromania.game.spaceship_shooter.individuals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Car extends Actor {
    private ShapeRenderer shapeRenderer;
    static private boolean projectionMatrixSet;
    private int x;
    private int y;
    private int width;
    private int height;
    private int rev = 1;
    public boolean goingRight = true;
    private Texture texture;

    public Rocket getiRocket() {
        return iRocket;
    }

    private Rocket iRocket = null;

    public Car(){
        shapeRenderer = new ShapeRenderer();
        projectionMatrixSet = false;
        width = Gdx.graphics.getWidth()/3;
        height= Gdx.graphics.getHeight()/8;
        y = 320;
        x = Gdx.graphics.getWidth()/2;
        texture = new Texture("spaceship_shooter/car_try.png");
    }

    public void turnSide(){
        rev *= -1;
        goingRight = !goingRight;
    }
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, x-rev*width/2, y-height/2, rev*width, height);
    }

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


    public void moveRight(float dt){
        if (x + width/2 <= Gdx.graphics.getWidth())
            x += dt * 250;

    }

    public void moveLeft(float dt){
        if (x - width/2 >= 0)
            x -= dt * 250;
    }
    public void shoot(){
        if ((iRocket == null) || (iRocket.reach_top())){
            this.iRocket = new Rocket(x, y);
        }
    }
    public boolean shooted(){
        return iRocket != null && !iRocket.reach_top();
    }


}
