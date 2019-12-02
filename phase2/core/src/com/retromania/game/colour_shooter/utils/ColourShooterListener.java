package com.retromania.game.colour_shooter.utils;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.retromania.game.colour_shooter.models.Bullet;
import com.retromania.game.colour_shooter.models.Square;

public class ColourShooterListener implements ContactListener {

    @Override
    public void beginContact(Contact contact) {
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();
        bulletCatcher(fa, fb);
        bulletCatcher(fb, fa);
    }
    private void bulletCatcher(Fixture fa, Fixture fb){
        if (fa.getUserData() != null & fb.getUserData() != null) {
            if (fa.getUserData().getClass().isAssignableFrom(Bullet.class) &
                    fb.getUserData().getClass().isAssignableFrom(Square.class)) {
                Bullet bullet = ((Bullet) fa.getUserData());
                Square square = ((Square) fb.getUserData());
                bullet.setBulletCollided(true);
                square.setBulletCollided(square.body.getAngle());
                System.out.println("INNER CONTACT");
          }
        }
        System.out.println("OUTER CONTACT");
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
