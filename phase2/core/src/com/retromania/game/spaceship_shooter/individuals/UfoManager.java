package com.retromania.game.spaceship_shooter.individuals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;
import java.util.List;


/**
 * The UFO Manager responsible for managing the list of UFOs through working
 * with the UFO Drawer, Mover, Repo actors through the UFO Manager Facade.
 * @author Thuy, Umid.
 */
public class UfoManager extends Actor {
    /**
     * The UFO Manager's Facade.
     */
    private UfoManagerFacade facade;

    /**
     * Initialize this UFO Manager by initialize its facade through a builder with
     * numOfUfos UFOs.
     *
     * @param numOfUfos
     */
    public UfoManager(int numOfUfos){
        facade = (new UfoManagerFacadeBuilder()).buildMover().buildRepo(numOfUfos).buildDrawer().buildFacade();
    }

    /**
     * Update all the UFOs that this UFO Manager manages, and also update the rocket.
     *
     * @param rocket the rocket in the screen that is fired by the car.
     * @param hud
     */
    public void update(Rocket rocket, Hud hud){
        facade.mover.moveUfos(facade.repo.getUfos());
        facade.mover.moveRocket(rocket, facade.repo.getUfos(), hud);
    }

    /**
     * draw the list of UFOs that this UFO Manager manages.
     *
     * @param batch
     * @param delta
     */
    public void draw(Batch batch, float delta){
        facade.drawer.drawUfos(batch, facade.repo.getUfos(), delta);
    }
}
