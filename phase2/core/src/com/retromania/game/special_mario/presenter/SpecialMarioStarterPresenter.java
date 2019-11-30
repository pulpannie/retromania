package com.retromania.game.special_mario.presenter;

import com.badlogic.gdx.physics.box2d.World;
import com.retromania.game.shared_abstractions.Presentable;
import com.retromania.game.special_mario.models.MainPlayer;
import com.retromania.game.special_mario.models.MainPlayerInput;

import javax.inject.Inject;

public class SpecialMarioStarterPresenter implements Presentable {


    MainPlayer mainPlayer;
    World world;

    @Inject
    SpecialMarioStarterPresenter(
            MainPlayer mainPlayer,
            World world){

        this.mainPlayer = mainPlayer;
        this.world = world;

    }

    public void setMainPlayerInput(MainPlayerInput mainPlayerInput){
        mainPlayer.setInput(mainPlayerInput);
    }

    @Override
    public void present() {
        world.step(1 / 60f, 6, 2);
        mainPlayer.update();
    }
}
