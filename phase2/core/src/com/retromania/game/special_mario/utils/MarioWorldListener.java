package com.retromania.game.special_mario.utils;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.retromania.game.special_mario.abstractions.TiledMapIndividual;
import com.retromania.game.special_mario.individuals.MainPlayer;

public class MarioWorldListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
        playerHitListener(fixA, fixB);
        playerHitListener(fixB, fixA);
    }

    private void playerHitListener(Fixture fixA, Fixture fixB){
        Object a = fixA.getUserData();
        Object b = fixB.getUserData();
        if (a == null || b == null){
            return;
        }
        if(MainPlayer.MainPlayerCollisionInfo.class.isAssignableFrom(a.getClass())){
            if(TiledMapIndividual.class.isAssignableFrom(b.getClass())){
                ((TiledMapIndividual)b).hitWithPlayer((MainPlayer.MainPlayerCollisionInfo)a);
            }
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

}
