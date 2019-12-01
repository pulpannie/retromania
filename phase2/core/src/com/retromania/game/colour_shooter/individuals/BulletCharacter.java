package com.retromania.game.colour_shooter.individuals;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.retromania.game.shared_abstractions.Character;
import com.retromania.game.shared_abstractions.RetroManiaModel;

public class BulletCharacter extends Character implements RetroManiaModel {

    private FixtureDef fixtureDef;
    private static int getRadius(){
        return 3;
    }
    public BulletCharacter(World given_world, TextureRegion textureRegion, int x, int y) {
        super(textureRegion, x, y, getRadius() * 4, getRadius() * 4, 1f, given_world, x, y);

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
        bDef.type = BodyDef.BodyType.DynamicBody;
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

    public void move(float speed_x, float speed_y) {
        this.body.setLinearVelocity(speed_x, speed_y);
    }

    @Override
    public void update() {
        setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);

    }

    @Override
    public void setInput(Object input) {

    }

    @Override
    public Object getOutput() {
        return null;
    }
}
