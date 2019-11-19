package com.retromania.game.special_mario.abstractions;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.retromania.game.special_mario.SpecialMarioStarter;
import com.retromania.game.special_mario.utils.MainPlayerCollisionInfo;
import com.retromania.game.special_mario.utils.BodyPart;

import static com.retromania.game.special_mario.SpecialMarioStarter.convertPixelToMeter;

public abstract class Character extends CharacterSprite implements Collidable {

    private World world;
    protected Body body;
    protected FixtureDef fixtureDef;


    public Character(){
        super();
        SpecialMarioStarter innerGame = SpecialMarioStarter.getSpecialMarioStarter();
        this.world = innerGame.getWorld();
        createMainPlayer();
    }

    private void createMainPlayer(){
        BodyDef bodyDef = setUpBodyDef();
        body = setUpBody(bodyDef);
        setUpFixture(7, 4);
        body.createFixture(fixtureDef).setUserData(new MainPlayerCollisionInfo(this, BodyPart.HEAD));
    }

    private BodyDef setUpBodyDef(){
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(convertPixelToMeter(32), convertPixelToMeter(256));
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        return bodyDef;
    }

    private Body setUpBody(BodyDef bodyDef){
        return world.createBody(bodyDef);
    }

    private void setUpFixture(float circleShapeRadius, float headLength){
        fixtureDef = new FixtureDef();
        setDefaultCategoryMask();
        setDefaultCollidableWith();
        setUpFixtureDefShapes(circleShapeRadius, headLength);
    }


    private void setUpFixtureDefShapes(float circleShapeRadius, float headLength){
        fixtureDef.shape = setUpCircleBody(circleShapeRadius);
        body.createFixture(fixtureDef);
        fixtureDef.shape = setUpEdgeShapeBody(circleShapeRadius, headLength);
        fixtureDef.isSensor = true;
    }

    private CircleShape setUpCircleBody(float circleShapeRadius){
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(convertPixelToMeter(circleShapeRadius));
        return circleShape;
    }

    private EdgeShape setUpEdgeShapeBody(float circleShapeRadius, float headLength){
        EdgeShape head = new EdgeShape();
        head.set(new Vector2(convertPixelToMeter(-headLength/2), convertPixelToMeter(circleShapeRadius)),
                new Vector2(convertPixelToMeter(headLength/2), convertPixelToMeter(circleShapeRadius)));
        return head;
    }

}
