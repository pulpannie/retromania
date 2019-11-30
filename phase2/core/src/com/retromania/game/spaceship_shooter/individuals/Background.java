package com.retromania.game.spaceship_shooter.individuals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
/**
 * Background of game, which remains unchanged during the game
 *
 * @author Umid, Thuy
 * */


public class Background extends Actor {

    /**
     * Variables:
     * shapeRenderer: renderer to draw simple objects
     * projectionMatrixSet: boolean for check if batch is set for projection
     * width: width of background
     * height: height of  background
     * */
    private ShapeRenderer shapeRenderer;
    private static boolean projectionMatrixSet;
    private int width;
    private int height;

    /**
     * constructor of Background
     * */
    public Background(){
        shapeRenderer = new ShapeRenderer();
        projectionMatrixSet = false;
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
    }
    /**
     * Method that draws background with shapeRenderer
     * */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.end();
        if(!projectionMatrixSet){
            shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        }

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.SKY);
        shapeRenderer.rect(0, height/8, width, 7*height/8);
        shapeRenderer.setColor(Color.CORAL);
        shapeRenderer.rect(0,0, width, height/8);
        shapeRenderer.end();
        batch.begin();
    }

}
