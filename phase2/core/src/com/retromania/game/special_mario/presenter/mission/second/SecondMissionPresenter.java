package com.retromania.game.special_mario.presenter.mission.second;

import com.badlogic.gdx.physics.box2d.World;
import com.retromania.game.special_mario.models.player.MainPlayer;
import com.retromania.game.special_mario.models.utils.LevelPreference;
import com.retromania.game.special_mario.presenter.MarioGamePresenter;

import javax.inject.Inject;

public class SecondMissionPresenter extends MarioGamePresenter {
    @Inject
    public SecondMissionPresenter(MainPlayer mainPlayer, World world, LevelPreference levelPreference) {
        super(mainPlayer, world, levelPreference);
    }
}
