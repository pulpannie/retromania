package com.retromania.game.special_mario.individuals;

import com.badlogic.gdx.maps.MapObject;
import com.retromania.game.special_mario.abstractions.TiledMapIndividual;
import com.retromania.game.special_mario.utils.MainPlayerCollisionInfo;

public class FriezingBlock extends TiledMapIndividual {
    public FriezingBlock(MapObject object) {
        super(object);
    }

    @Override
    public void hitWithPlayer(MainPlayerCollisionInfo playerCollisionInfo) {
        System.out.println("things are about to go down");
        System.out.println(getFixtureDef().filter.categoryBits);
        System.out.println(playerCollisionInfo.getCharacter().getFixtureDef().filter.maskBits);
    }

    @Override
    public void update(Object... args) {

    }


    @Override
    public short getDefaultMask() {
        return 16;
    }

    @Override
    public short getDefaultTarget() {
        return 0;
    }
}
