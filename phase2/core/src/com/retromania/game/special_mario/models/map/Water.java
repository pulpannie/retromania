package com.retromania.game.special_mario.models.map;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.physics.box2d.World;
import com.retromania.game.special_mario.abstractions.TiledMapIndividual;
import com.retromania.game.special_mario.models.player.MainPlayer;
import com.retromania.game.special_mario.models.player.MainPlayerCollisionInfo;

/**
 * The logic and tile for waters in the game
 * **/
public class Water extends TiledMapIndividual {


    public Water(MapObject object, World world) {
        super(object, world);
        setCategoryMask((short) 0);
        setCollidableWith((short) 0);
    }

    @Override
    public void hitWithPlayer(MainPlayerCollisionInfo playerCollisionInfo) {

    }

    @Override
    public void hitWithBodyOfMainPlayer(MainPlayer mainPlayer) {
    }

    @Override
    public void update(Object... args) {

    }
}
