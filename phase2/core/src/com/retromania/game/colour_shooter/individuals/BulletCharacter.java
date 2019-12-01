package com.retromania.game.colour_shooter.individuals;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.retromania.game.shared_abstractions.Character;

public class BulletCharacter extends Character {

    private float radiusBullet;
    private float x_pos, y_pos;
    private FixtureDef fixtureDef;

    public BulletCharacter(World given_world, TextureRegion textureRegion, int x, int y, int radius) {
        super(textureRegion, x, y, radius * 2, radius * 2, 1f, given_world);
        x_pos = x;
        y_pos = y;
        radiusBullet = radius;
    }

    @Override
    protected Object getUserData() {
        return this;
    }

    @Override
    protected BodyDef setUpBodyDef() {
        BodyDef bDef = new BodyDef();
        bDef.fixedRotation = true;
        bDef.type = BodyDef.BodyType.DynamicBody;
        bDef.position.set(x_pos, y_pos);
        return bDef;
    }

    @Override
    protected Body setUpBody(BodyDef bDef) {
        this.body = getWorld().createBody(bDef);
        return body;
    }

    @Override
    protected void setUpFixture() {
        CircleShape shape = new CircleShape();
        shape.setRadius(radiusBullet);

        fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        body.createFixture(fixtureDef);
    }

    @Override
    public FixtureDef getFixtureDef() {
        return fixtureDef;
    }

    @Override
    public short getDefaultMask() {
        return 0;
    }

    @Override
    public short getDefaultTarget() {
        return 0;
    }
}
