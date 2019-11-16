package com.retromania.game.spaceship_shooter.individuals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;
import java.util.List;

public class UfoManager extends Actor {
    private UfoManagerFacade facade;

    public UfoManager(int numOfUfos){
        facade = (new UfoManagerFacadeBuilder()).buildMover().buildRepo(numOfUfos).buildDrawer().buildFacade();
    }

    public void update(Rocket rocket, Hud hud){
        facade.mover.moveUfos(facade.repo.getUfos());
        facade.mover.moveRocket(rocket, facade.repo.getUfos(), hud);
    }

    public void draw(Batch batch, float delta){
        facade.drawer.drawUfos(batch, facade.repo.getUfos(), delta);
    }
}
