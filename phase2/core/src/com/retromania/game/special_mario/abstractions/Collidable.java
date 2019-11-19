package com.retromania.game.special_mario.abstractions;

import com.badlogic.gdx.physics.box2d.Fixture;

public interface Collidable {
    Fixture getFixture();
    default void a(){

    }
}
