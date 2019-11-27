package com.retromania.game.special_mario.individuals;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.retromania.game.shared_abstractions.Character;
import com.retromania.game.shared_abstractions.Individual;
import com.retromania.game.special_mario.utils.WorldInformation;

import static com.retromania.game.special_mario.SpecialMarioConfiguration.convertPixelToMeter;
import static com.retromania.game.special_mario.SpecialMarioConfiguration.getPixelToMeterConversionRate;

public class MainPlayer extends Character implements Individual {

  public MainPlayer(WorldInformation worldInformation) {
    super(
        worldInformation.getTextureAtlas().findRegion("mario_small"),
        0,
        0,
        16,
        16,
        getPixelToMeterConversionRate(),
        worldInformation.getWorld());
  }

  public void handleInput(
      int worldWidth,
      int worldHeight,
      int X,
      int Y,
      boolean hasBeenTouched,
      boolean hasBeenHeldDown) {
    System.out.println(worldWidth +" "+ X);
    updateX(worldWidth, X, hasBeenHeldDown);
    updateY(worldHeight, Y, hasBeenTouched);
  }

  private void updateX(float worldWidth, int X, boolean hasBeenHeldDown) {
    if (hasBeenHeldDown && Math.abs(body.getLinearVelocity().x) < 2) {
      if (X <= worldWidth / 2)
        body.applyLinearImpulse(new Vector2(-.1f, 0), body.getWorldCenter(), true);
      else body.applyLinearImpulse(new Vector2(.1f, 0), body.getWorldCenter(), true);
    }
  }

  private void updateY(float worldHeight, int Y, boolean hasBeenTouched) {
    if (hasBeenTouched) {
      if (Y <= worldHeight / 2)
        body.applyLinearImpulse(new Vector2(0, 4f), body.getWorldCenter(), true);
    }
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

  /**
   * args [0] : The worldWidth args [1] : The worldHeight args[2] : The input X args[3] : The input
   * Y
   *
   * <p>*
   *
   * <p>args[4] : whether or not the screen has been touched args[5] : whether or not the screen has
   * been held down
   */
  @Override
  public void update(Object... args) {
    assertInput(args);
    handleInput(
        (Integer) args[0],
        (Integer) args[1],
        (Integer) args[2],
        (Integer) args[3],
        (Boolean) args[4],
        (Boolean) args[5]);
    setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
  }

  private void assertInput(Object[] args) {
    if (!args[0].getClass().isAssignableFrom(Integer.class)
        || !args[1].getClass().isAssignableFrom(Integer.class)
        || !args[2].getClass().isAssignableFrom(Integer.class)
        || !args[3].getClass().isAssignableFrom(Integer.class)
        || !args[4].getClass().isAssignableFrom(Boolean.class)
        || !args[5].getClass().isAssignableFrom(Boolean.class)) {
      throw new RuntimeException("The input format is not correct.");
    }
  }

  @Override
  protected Object getUserData() {
    return null;
  }

  @Override
  protected BodyDef setUpBodyDef() {
    BodyDef bodyDef = new BodyDef();
    bodyDef.position.set(convertPixelToMeter(32), convertPixelToMeter(32));
    bodyDef.type = BodyDef.BodyType.DynamicBody;
    return bodyDef;
  }

  @Override
  protected Body setUpBody(BodyDef bodyDef) {
    return getWorld().createBody(bodyDef);
  }

  @Override
  protected void setUpFixture() {
    fixtureDef = new FixtureDef();
    setDefaultCategoryMask();
    setDefaultCollidableWith();
    setUpFixtureDefShapes(7, 4);
  }

  private void setUpFixtureDefShapes(float circleShapeRadius, float headLength) {
    fixtureDef.shape = setUpCircleBody(circleShapeRadius);
    body.createFixture(fixtureDef);
    fixtureDef.shape = setUpEdgeShapeBody(circleShapeRadius, headLength);
    fixtureDef.isSensor = true;
  }

  private CircleShape setUpCircleBody(float circleShapeRadius) {
    CircleShape circleShape = new CircleShape();
    circleShape.setRadius(convertPixelToMeter(circleShapeRadius));
    return circleShape;
  }

  private EdgeShape setUpEdgeShapeBody(float circleShapeRadius, float headLength) {
    EdgeShape head = new EdgeShape();
    head.set(
        new Vector2(convertPixelToMeter(-headLength / 2), convertPixelToMeter(circleShapeRadius)),
        new Vector2(convertPixelToMeter(headLength / 2), convertPixelToMeter(circleShapeRadius)));
    return head;
  }
}
