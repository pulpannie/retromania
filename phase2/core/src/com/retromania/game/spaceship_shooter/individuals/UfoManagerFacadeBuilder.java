package com.retromania.game.spaceship_shooter.individuals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.ArrayList;
import java.util.List;

public class UfoManagerFacadeBuilder {
    UfoDrawer drawer;
    UfoMover mover;
    UfoRepo repo;

    public UfoManagerFacadeBuilder buildDrawer(){
        drawer = new UfoDrawer();
        return this;
    }

    public UfoManagerFacadeBuilder buildRepo(int numOfUfos){
        List<UFO> ufos = new  ArrayList<UFO>();
        double tempy = 0;
        int height = Gdx.graphics.getHeight();
        for(int i = 0; i< numOfUfos; i++){
            tempy = Math.random() * (height/2 + 1) + height/4;
            ufos.add(new UFO(-200*i,(int) tempy));

        }

        repo = new UfoRepo(ufos);
        return  this;
    }

    public UfoManagerFacadeBuilder buildMover(){
        mover = new UfoMover();
        return this;
    }

    public UfoManagerFacade buildFacade(){return new UfoManagerFacade(drawer, mover, repo);}

}
