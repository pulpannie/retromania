package com.retromania.game.spaceship_shooter.individuals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Rocket extends Actor {
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

    public Rocket(int x, int y){
        width = Gdx.graphics.getWidth()/10;
        height = Gdx.graphics.getHeight()/16;
        this.x = x;
        this.y = y;
        shapeRenderer = new ShapeRenderer();
        projectionMatrixSet = false;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    public boolean reach_top(){
        return y+ height >= Gdx.graphics.getHeight();
    }
    public  void moveUp(){
        this.y += 50;
    }



    public void draw_2(Batch batch, float parentAlpha) {
        batch.end();
        if(!projectionMatrixSet){
            shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
            projectionMatrixSet = true;
        }
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(x-width/2, y-height/2, width, height);
        shapeRenderer.end();
        batch.begin();
    }

    public void draw(Batch batch, float parentAlpha){
        batch.draw(texture, x-width/2, y-height/2, width, height);
    }
}
