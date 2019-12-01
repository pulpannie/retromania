package com.retromania.game.colour_shooter.individuals;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.retromania.game.colour_shooter.utils.Constants;

public class Bullet {
    public Body body;
    public String id;

    public Bullet(World world, String id, float x, float y, float radius) {
        this.id = id;
        createBoxBody(world, x, y, radius);
    }

    private void createBoxBody(World world, float x, float y, float radius) {
        BodyDef bDef = new BodyDef();
        bDef.fixedRotation = true;
        bDef.type = BodyDef.BodyType.DynamicBody;
        bDef.position.set(x, y);

        CircleShape shape = new CircleShape();
        shape.setRadius(radius);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;

        this.body = world.createBody(bDef);
        this.body.createFixture(fixtureDef).setUserData(this);
    }
}

