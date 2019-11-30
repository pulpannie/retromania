package com.retromania.game.colour_shooter.individuals;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.retromania.game.colour_shooter.screens.PlayScreen;
import com.retromania.game.shared_abstractions.Character;
import com.retromania.game.shared_abstractions.Individual;

public class BulletCatcherSquare extends Character implements Individual {

  public BulletCatcherSquare(
      TextureRegion textureRegion,
      int x,
      int y,
      int width,
      int height,
      float pixelToMeterRate,
      World world) {
    super(textureRegion, x, y, width, height, pixelToMeterRate, world);
  }

  @Override
  protected Object getUserData() {
    return null;
  }

  @Override
  protected BodyDef setUpBodyDef() {
    BodyDef bodyDef = new BodyDef();
    bodyDef.position.set(
        PlayScreen.viewport.getWorldWidth() / 2, (float) (PlayScreen.viewport.getWorldHeight() * 0.6));
    bodyDef.type = BodyDef.BodyType.DynamicBody;
    return bodyDef;
  }

  @Override
  protected Body setUpBody(BodyDef bodyDef) {
    return getWorld().createBody(bodyDef);
  }

  private CircleShape setUpCircleBody(float circleShapeRadius) {
    CircleShape circleShape = new CircleShape();
    circleShape.setRadius(circleShapeRadius);
     return circleShape;
  }

  private EdgeShape setUpEdgeShapeBodyHorizontal(float circleShapeRadius, float headLength) {
    EdgeShape head = new EdgeShape();
    head.set(
        new Vector2((-headLength / 2), (circleShapeRadius)),
        new Vector2((headLength / 2), (circleShapeRadius)));
    return head;
  }

  private EdgeShape setUpEdgeShapeBodyVertical(float circleShapeRadius, float headLength) {
    EdgeShape head = new EdgeShape();
    head.set(
            new Vector2((circleShapeRadius), (-headLength / 2)),
            new Vector2((circleShapeRadius), (headLength / 2)));
    return head;
  }

  private void setUpFixtureDefShapes(float circleShapeRadius, float headLength) {
    fixtureDef.shape = setUpCircleBody(circleShapeRadius);
    body.createFixture(fixtureDef);

    fixtureDef.shape = setUpEdgeShapeBodyHorizontal(circleShapeRadius, headLength);
    fixtureDef.isSensor = true;
    body.createFixture(fixtureDef);

    fixtureDef.shape = setUpEdgeShapeBodyVertical(circleShapeRadius, headLength);
    fixtureDef.isSensor = true;
    body.createFixture(fixtureDef);

    fixtureDef.shape = setUpEdgeShapeBodyVertical(-circleShapeRadius, headLength);
    fixtureDef.isSensor = true;
    body.createFixture(fixtureDef);

    fixtureDef.shape = setUpEdgeShapeBodyHorizontal(-circleShapeRadius, headLength);
    fixtureDef.isSensor = true;
    body.createFixture(fixtureDef);

  }

  @Override
  protected void setUpFixture() {
    fixtureDef = new FixtureDef();
    setDefaultCategoryMask();
    setDefaultCollidableWith();
    setUpFixtureDefShapes(32, 64);
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


  @Override
  public void update(Object... args) {
    //        body.getPosition().set(10000, 100000);
  }
}
