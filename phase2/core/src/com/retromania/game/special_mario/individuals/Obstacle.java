package com.retromania.game.special_mario.individuals;

import com.badlogic.gdx.maps.MapObject;
import com.retromania.game.special_mario.abstractions.TiledMapIndividual;

public class Obstacle extends TiledMapIndividual {
    public Obstacle(MapObject object) {
        super(object);
    }

    @Override
    public void hitWithPlayer(MainPlayer.MainPlayerCollisionInfo playerCollisionInfo) {

    }

    @Override
    public void update(Object... args) {

    }
}
