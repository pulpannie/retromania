package com.retromania.game.mario.utils;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.retromania.game.mario.abstractions.TiledMapIndividual;
import com.retromania.game.mario.models.player.MainPlayer;
import com.retromania.game.mario.models.player.MainPlayerCollisionInfo;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MarioWorldListener implements ContactListener {

  private MainPlayer mainPlayer;

  @Inject
  public MarioWorldListener(MainPlayer mainPlayer){
    this.mainPlayer = mainPlayer;
  }

  @Override
  public void beginContact(Contact contact) {
    Fixture fixA = contact.getFixtureA();
    Fixture fixB = contact.getFixtureB();
    playerHitListener(fixA, fixB);
    playerHitListener(fixB, fixA);
  }

  private void playerHitListener(Fixture fixA, Fixture fixB) {
    Object a = fixA.getUserData();
    Object b = fixB.getUserData();
    if (b == null) {
      return;
    }
    if (a == null) {
      if (TiledMapIndividual.class.isAssignableFrom(b.getClass())) {

        ((TiledMapIndividual) b).hitWithBodyOfMainPlayer(mainPlayer);
      }
    } else if (TiledMapIndividual.class.isAssignableFrom(b.getClass())) {
      if (MainPlayerCollisionInfo.class.isAssignableFrom(a.getClass())) {
        ((TiledMapIndividual) b).hitWithPlayer((MainPlayerCollisionInfo) a);
      }
    }
  }

  @Override
  public void endContact(Contact contact) {}

  @Override
  public void preSolve(Contact contact, Manifold oldManifold) {}

  @Override
  public void postSolve(Contact contact, ContactImpulse impulse) {}
}
