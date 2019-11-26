package com.retromania.game.spaceship_shooter.individuals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.ArrayList;
import java.util.List;

/**
 * The UFO Manager Facade Builder responsible for building the UFO Manager Facade.
 * @author Thuy, Umid.
 */
public class UfoManagerFacadeBuilder {
    /**
     * The UFO drawer responsible for drawing the list of UFOs.
     */
    UfoDrawer drawer;

    /**
     * The UFO mover responsible for moving the list of UFOs.
     */
    UfoMover mover;

    /**
     * The UFO repo responsible for storing the list of UFOs.
     */
    UfoRepo repo;

    /**
     * Initialize the UFO drawer.
     */
    public UfoManagerFacadeBuilder buildDrawer(){
        drawer = new UfoDrawer();
        return this; //By returning the builder each time, we can create a fluent interface.
    }

    /**
     * Initialize the UFO repo with the numOfUfos UFOs.
     * @param numOfUfos   the number of UFOs instantiated in the UFO repo.
     */
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

    /**
     * Initialize the UFO mover.
     */
    public UfoManagerFacadeBuilder buildMover(){
        mover = new UfoMover();
        return this;
    }

    /**
     * Initialize the UFo Manager Facade with the UFO Drawer, Mover and Repo.
     *
     * @return the UFO Manager Facade.
     */
    public UfoManagerFacade buildFacade(){return new UfoManagerFacade(drawer, mover, repo);}

}
