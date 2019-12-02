package com.retromania.game.colour_shooter.models;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.retromania.game.shared_abstractions.Character;
import com.retromania.game.shared_abstractions.Collidable;

public class Square extends Character implements Collidable {
    private FixtureDef fixtureDef;
    private float angleSquare;

    private float getRadius() {
        return 8;
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

    public float getAngleFinished() {
        return angleSquare;
    }

    public void setBulletCollided(float angle) {
        this.angleSquare = (float) Math.toDegrees(angle) % 360;
    }

    @Override
    protected Object getUserData() {
        return this;
    }

    @Override
    protected BodyDef setUpBodyDef() {
        BodyDef bDef = new BodyDef();
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
        shape.setAsBox((float) (getRadius() * 4.6875), (float) (getRadius() * 4.6875));
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

    public void rotateSquare() {
        this.body.setAngularVelocity(1.5f);
        float curr = this.body.getAngle();
//    float curr = getRotation();
//    curr  = curr >= 360 ? 0 : curr + 90/60f;
        setRotation(curr * (180f / (float) Math.PI));
        setOriginCenter();
//    rotateBody(curr);
    }

    private void rotateBody(float currentRotation) {
    }

    public void increaseWeight(float num) {
        this.body.setLinearDamping(num);
    }
}
