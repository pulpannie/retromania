package com.retromania.game.shared_abstractions;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.retromania.game.special_mario.abstractions.Collidable;


public abstract class Character extends CharacterSprite implements Collidable {

    private World world;
    protected Body body;
    protected FixtureDef fixtureDef;


    public Character(TextureRegion textureRegion, int x, int y, int width, int height, float pixelToMeterRate, World world){
        super(textureRegion,x, y, width, height, pixelToMeterRate);
        this.world = world;
        createMainPlayer();
    }

    private void createMainPlayer(){
        BodyDef bodyDef = setUpBodyDef();
        body = setUpBody(bodyDef);
        setUpFixture();
        body.createFixture(fixtureDef).setUserData(getUserData());
    }

    protected abstract Object getUserData();

    protected abstract BodyDef setUpBodyDef();

    protected abstract Body setUpBody(BodyDef bodyDef);

    protected abstract void setUpFixture();


    protected World getWorld() {
        return this.world;
    }

}
