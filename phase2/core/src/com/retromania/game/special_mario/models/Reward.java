package com.retromania.game.special_mario.models;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.physics.box2d.World;
import com.retromania.game.special_mario.abstractions.TiledMapIndividual;
import com.retromania.game.special_mario.models.player.MainPlayerCollisionInfo;

public class Reward extends TiledMapIndividual {

    public Reward(MapObject object, World world) {
        super(object, world);
    }

    @Override
    public void hitWithPlayer(MainPlayerCollisionInfo playerCollisionInfo) {

    }

    @Override
    public void update(Object... args) {

    }
}
