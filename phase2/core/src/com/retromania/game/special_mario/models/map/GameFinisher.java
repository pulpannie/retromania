package com.retromania.game.special_mario.models.map;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.physics.box2d.World;
import com.retromania.game.special_mario.abstractions.TiledMapIndividual;
import com.retromania.game.special_mario.models.player.MainPlayer;
import com.retromania.game.special_mario.models.player.MainPlayerCollisionInfo;


public class GameFinisher extends TiledMapIndividual {
    public GameFinisher(MapObject object, World world) {
        super(object, world);
    }

    @Override
    public void hitWithPlayer(MainPlayerCollisionInfo playerCollisionInfo) {
    }

    @Override
    public void hitWithBodyOfMainPlayer(MainPlayer mainPlayer) {
        mainPlayer.setFinished(true);
    }

    @Override
    public void update(Object... args) {

    }

    @Override
    public short getDefaultMask() {
        return 2;
    }

    @Override
    public short getDefaultTarget() {
        return -1;
    }
}
