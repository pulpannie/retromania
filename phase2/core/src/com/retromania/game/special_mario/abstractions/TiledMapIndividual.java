package com.retromania.game.special_mario.abstractions;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.retromania.game.shared_abstractions.Individual;
import com.retromania.game.special_mario.SpecialMarioStarter;

import static com.retromania.game.special_mario.SpecialMarioStarter.convertPixelToMeter;

public abstract class TiledMapIndividual implements Individual {

    protected Rectangle bound;
    private Body body;
    protected Fixture fixture;


    public TiledMapIndividual(MapObject object){


        BodyDef bodyDef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fixtureDef = new FixtureDef();



        SpecialMarioStarter innerGame = SpecialMarioStarter.getSpecialMarioStarter();
        bound = ((RectangleMapObject) object).getRectangle();
        bodyDef.type = BodyDef.BodyType.StaticBody;
        bodyDef.position.set(
                convertPixelToMeter(bound.getX() + bound.getWidth() / 2),
                convertPixelToMeter(bound.getY() + bound.getHeight() / 2));
        body = innerGame.getWorld().createBody(bodyDef);

        shape.setAsBox(
                convertPixelToMeter(bound.getWidth() / 2),
                convertPixelToMeter(bound.getHeight() / 2));
        fixtureDef.shape = shape;
        fixture = body.createFixture(fixtureDef);
        fixture.setUserData(this);
    }

    public abstract void hatHit();

}
