package com.retromania.game.special_mario.individuals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.retromania.game.shared_abstractions.Character;
import com.retromania.game.shared_abstractions.Individual;
import com.retromania.game.special_mario.SpecialMarioStarter;
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

  public void handleInput() {
    SpecialMarioStarter innerGame = SpecialMarioStarter.getSpecialMarioStarter();
    if (Gdx.input.isTouched() && Math.abs(body.getLinearVelocity().x) < 2) {
      if (Gdx.input.getX() <= innerGame.gamePort.getScreenWidth() / 2)
        body.applyLinearImpulse(new Vector2(-.1f, 0), body.getWorldCenter(), true);
      else body.applyLinearImpulse(new Vector2(.1f, 0), body.getWorldCenter(), true);
    }
    if (Gdx.input.justTouched()) {
      if (Gdx.input.getY() <= innerGame.gamePort.getScreenHeight() / 2)
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

  @Override
  public void update(Object... args) {
    handleInput();
    setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
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


