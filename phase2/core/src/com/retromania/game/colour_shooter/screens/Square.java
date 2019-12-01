package com.retromania.game.colour_shooter.screens;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.retromania.game.colour_shooter.utils.Constants;

public class Square {
    public Body body;
    public String id;
    public Square(World world, String id, float x, float y, float w, float h) {
        this.id = id;
        createBoxBody(world, x, y, w, h);
    }

    private void createBoxBody(World world, float x, float y, float w, float h) {
        BodyDef bDef = new BodyDef();
        bDef.fixedRotation = true;
        bDef.type = BodyDef.BodyType.DynamicBody;
        bDef.position.set(x, y);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(w, h);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1.0f;

        this.body = world.createBody(bDef);
        this.body.createFixture(fixtureDef).setUserData(this);
    }

    public void hit() {
        System.out.println(id + ":I'have been hit");
    }
}

