package com.retromania.game.special_mario.models.player;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.retromania.game.shared_abstractions.Character;
import com.retromania.game.shared_abstractions.RetroManiaModel;
import com.retromania.game.special_mario.models.MainPlayerInput;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import static com.retromania.game.special_mario.SpecialMarioConfiguration.getPixelToMeterConversionRate;

@Singleton
public class MainPlayer extends Character implements RetroManiaModel<MainPlayerInput> {

  MainPlayerInput input;

  private static TextureRegion getTextureRegion(String regionName, TextureAtlas textureAtlas) {
    return textureAtlas.findRegion(regionName);
  }

  @Inject
  public MainPlayer(
      @Named("Texture Region Name") String textureRegionName,
      TextureAtlas textureAtlas,
      @Named("X") int x,
      @Named("Y") int y,
      World world,
      @Named("INIT X IN WORLD") int initialXInWorld,
      @Named("INIT Y IN WORLD") int initialYInWorld) {
    super(
        getTextureRegion(textureRegionName, textureAtlas),
        x,
        y,
        getTextureRegion(textureRegionName, textureAtlas).getRegionWidth(),
        getTextureRegion(textureRegionName, textureAtlas).getRegionHeight(),
        getPixelToMeterConversionRate(),
        world,
        initialXInWorld,
        initialYInWorld);
  }

  public void handleInput() {
    updateX(input.getWorldWidth(), input.getX(), input.hasBeenHeldDown());
    updateY(input.getWorldHeight(), input.getY(), input.hasBeenTouched());
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

  @Override
  protected Object getUserData() {
    return new MainPlayerCollisionInfo(this, BodyPart.HEAD);
  }

  @Override
  protected BodyDef setUpBodyDef() {
    BodyDef bodyDef = new BodyDef();
    bodyDef.position.set(
        convertPixelToMeter(getInitialXInTheWorld()), convertPixelToMeter(getInitialYInTheWorld()));
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
    body.createFixture(fixtureDef);

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

  @Override
  public void update() {
    handleInput();
    setPosition(body.getPosition().x - getWidth() / 2, body.getPosition().y - getHeight() / 2);
  }

  @Override
  public void setInput(MainPlayerInput input) {
    this.input = input;
  }

  @Override
  public MainPlayerInput getOutput() {
    return input;
  }
}
