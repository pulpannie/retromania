package com.retromania.game.special_mario.individuals;

import com.badlogic.gdx.maps.MapObject;
import com.retromania.game.special_mario.abstractions.TiledMapIndividual;
import com.retromania.game.special_mario.utils.MainPlayerCollisionInfo;
import com.retromania.game.special_mario.utils.WorldInformation;

public class Fire extends TiledMapIndividual {

    public Fire(MapObject object, WorldInformation worldInformation) {
        super(object, worldInformation);
    }

    @Override
    public void hitWithPlayer(MainPlayerCollisionInfo playerCollisionInfo) {

    }

    @Override
    public void update(Object... args) {

    }

}
