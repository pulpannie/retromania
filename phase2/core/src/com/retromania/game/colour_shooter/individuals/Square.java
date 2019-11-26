package com.retromania.game.colour_shooter.individuals;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.retromania.game.shared_abstractions.Character;

public class Square extends Character {
    public Square(TextureRegion textureRegion, int x, int y, int width,
                  int height, float pixelToMeterRate, World world) {
        super(textureRegion, x, y, width, height, pixelToMeterRate, world);
    }

    @Override
    protected Object getUserData() {
        return null;
    }

    @Override
    protected BodyDef setUpBodyDef() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(32 / 100f, 256 / 100f);
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        return bodyDef;
    }

    @Override
    protected Body setUpBody(BodyDef bodyDef) {
        return getWorld().createBody(bodyDef);
    }

    private CircleShape setUpCircleBody(float circleShapeRadius) {
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(circleShapeRadius / 100f);
        return circleShape;
    }

    private EdgeShape setUpEdgeShapeBody(float circleShapeRadius, float headLength) {
        EdgeShape head = new EdgeShape();
        head.set(
                new Vector2((-headLength / 2) / 100f, (circleShapeRadius) / 100f),
                new Vector2((headLength / 2) / 100f, (circleShapeRadius) / 100f));
        return head;
    }

    private void setUpFixtureDefShapes(float circleShapeRadius, float headLength) {
        fixtureDef.shape = setUpCircleBody(circleShapeRadius);
        body.createFixture(fixtureDef);
        fixtureDef.shape = setUpEdgeShapeBody(circleShapeRadius, headLength);
        fixtureDef.isSensor = true;
    }

    @Override
    protected void setUpFixture() {
        fixtureDef = new FixtureDef();
        setDefaultCategoryMask();
        setDefaultCollidableWith();
        setUpFixtureDefShapes(7, 4);
    }

    @Override
    public FixtureDef getFixtureDef() {
        return fixtureDef;
    }

    @Override
    public short getDefaultMask() {
        return 2;
    }

    @Override
    public short getDefaultTarget() {
        return 4;
    }
}
