package com.retromania.game.mario.models.map;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.physics.box2d.World;
import com.retromania.game.mario.abstractions.TiledMapIndividual;
import com.retromania.game.mario.models.player.MainPlayer;
import com.retromania.game.mario.models.player.MainPlayerCollisionInfo;

/**
 *
 * The logic for the tile that gives the player rewards
 *
 * **/
public class Reward extends TiledMapIndividual {

    public Reward(MapObject object, World world) {
        super(object, world);
    }

    @Override
    public void hitWithPlayer(MainPlayerCollisionInfo playerCollisionInfo) {
        setCategoryMask((short)0);
        setCollidableWith((short) 0);
        playerCollisionInfo.getMainPlayer().addReward();
    }

    @Override
    public void hitWithBodyOfMainPlayer(MainPlayer mainPlayer) {

    }

    @Override
    public void update(Object... args) {

    }
}
