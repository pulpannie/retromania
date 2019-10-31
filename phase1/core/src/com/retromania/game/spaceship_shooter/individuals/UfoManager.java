package com.retromania.game.spaceship_shooter.individuals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;
import java.util.List;

public class UfoManager extends Actor {
    int height;
    private int numOfUfos;
    private List<UFO> UFOList;

    public UfoManager(int numOfUfos){
        this.numOfUfos = numOfUfos;
        UFOList = new ArrayList<UFO>();
        height = Gdx.graphics.getHeight();

        double tempy;
        for(int i = 0; i< numOfUfos; i++){
            tempy= Math.random() * (height/2 + 1) + height/4;
            UFOList.add(new UFO(-200*i,(int) tempy));

        }
    }

    public void update(Rocket rocket, Hud hud){
        for (UFO i : UFOList)
            i.moveRight();

        if (rocket != null && !rocket.reach_top() ){
            for (UFO i: UFOList){
                if(i.isRocketTouches(rocket)){
                    hud.addScore(10);
                    i.recreate();
                }
            }
        }

    }

    public void draw(Batch batch, float delta){
        for (UFO i: UFOList)
            i.draw(batch, delta);
    }
}
