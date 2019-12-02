package com.retromania.game.mario.presenter;

import com.badlogic.gdx.physics.box2d.World;
import com.retromania.game.mario.abstractions.RestartableObservable;
import com.retromania.game.mario.abstractions.RestartableObserver;
import com.retromania.game.mario.models.player.MainPlayer;
import com.retromania.game.mario.models.utils.LevelPreference;
import com.retromania.game.mario.utils.MarioWorldListener;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SpecialMarioStarterPresenter extends MarioGamePresenter implements RestartableObservable {


    @Inject
    SpecialMarioStarterPresenter(
            MainPlayer mainPlayer,
            World world,
            LevelPreference levelPreference, MarioWorldListener marioWorldListener){
        super(mainPlayer, world, levelPreference, marioWorldListener);
    }

    @Override
    public void present() {
        if (mainPlayer.isDead()||mainPlayer.isFinished()){
            resetPlayer();
            updateRestartableObservers();
        }
        super.present();
    }

    private List<RestartableObserver> observers = new ArrayList<>();

    @Override
    public void updateRestartableObservers() {
        for (RestartableObserver r : observers){
            r.restart();
        }
    }

    @Override
    public void addRestartableObserver(RestartableObserver restartableObserver) {
        observers.add(restartableObserver);
    }
}
