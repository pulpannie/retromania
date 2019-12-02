package com.retromania.game.colour_shooter.individuals;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.retromania.game.shared_abstractions.Character;
import com.retromania.game.shared_abstractions.Collidable;

/**
 * THIS CLASS IS RESPONSIBLE FOR MAKING THE
 * SQUARE WHICH IS THE ROTATING SQUARE.
 */
public class Square extends Character implements Collidable {
    private BodyDef bDef;
    private FixtureDef fixtureDef;

    private float getRadius() {
        return 16;
    }

    public Square(World given_world, TextureRegion textureRegion, int x, int y, int radius) {
        super(
                textureRegion,
                0,
                0,
                textureRegion.getRegionWidth(),
                textureRegion.getRegionHeight(),
                1f,
                given_world,
                x,
                y);
    }

    @Override
    protected Object getUserData() {
        return this;
    }

    @Override
    protected BodyDef setUpBodyDef() {
        bDef = new BodyDef();
        bDef.position.set(getInitialXInTheWorld(), getInitialYInTheWorld());
        bDef.fixedRotation = true;
        bDef.type = BodyDef.BodyType.KinematicBody;
        return bDef;
    }

    @Override
    protected Body setUpBody(BodyDef bDef) {
        this.body = getWorld().createBody(bDef);
        return body;
    }

    @Override
    protected void setUpFixture() {
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(getRadius() * 2, getRadius() * 2);
        fixtureDef = new FixtureDef();


        setDefaultCategoryMask();
        setDefaultCollidableWith();

        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
    }

    @Override
    public FixtureDef getFixtureDef() {
        return fixtureDef;
    }

    @Override
    public short getDefaultMask() {
        return -1;
    }

    @Override
    public short getDefaultTarget() {
        return -1;
    }

    public void rotateSquare(){
        this.body.setAngularVelocity(1.5f);
        float curr = this.body.getAngle();
        setRotation(curr*(180f/(float) Math.PI));
        setOriginCenter();

    }

}
