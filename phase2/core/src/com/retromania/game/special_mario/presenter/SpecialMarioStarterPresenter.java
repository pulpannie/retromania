package com.retromania.game.special_mario.presenter;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;
import com.retromania.game.special_mario.models.player.MainPlayer;
import com.retromania.game.special_mario.models.map.MainPlayerInput;
import com.retromania.game.special_mario.models.utils.LevelPreference;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SpecialMarioStarterPresenter extends MarioGamePresenter {


    @Inject
    SpecialMarioStarterPresenter(
            MainPlayer mainPlayer,
            World world,
            LevelPreference levelPreference){
        super(mainPlayer, world, levelPreference);
    }




}
