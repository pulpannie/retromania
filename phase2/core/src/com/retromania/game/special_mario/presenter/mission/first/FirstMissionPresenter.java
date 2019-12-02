package com.retromania.game.special_mario.presenter.mission.first;

import com.badlogic.gdx.physics.box2d.World;
import com.retromania.game.special_mario.models.player.MainPlayer;
import com.retromania.game.special_mario.models.utils.LevelPreference;
import com.retromania.game.special_mario.presenter.MarioGamePresenter;
import com.retromania.game.special_mario.utils.MarioWorldListener;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class FirstMissionPresenter extends MarioGamePresenter {
    @Inject
    public FirstMissionPresenter(
            MainPlayer mainPlayer,
            World world,
            LevelPreference levelPreference, MarioWorldListener marioWorldListener){
        super(mainPlayer, world, levelPreference, marioWorldListener);
    }
}
