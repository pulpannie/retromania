package com.retromania.game.shared_abstractions;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public abstract class Character extends CharacterSprite implements Collidable {

  private World world;
  public Body body;
  protected FixtureDef fixtureDef;

  private int initialXInTheWorld;
  private int initialYInTheWorld;

  public Character(
      TextureRegion textureRegion,
      int x,
      int y,
      int width,
      int height,
      float pixelToMeterRate,
      World world,
      int initialXInTheWorld,
      int initialYInTheWorld) {
    super(textureRegion, x, y, width, height, pixelToMeterRate);
    this.initialXInTheWorld = initialXInTheWorld;
    this.initialYInTheWorld = initialYInTheWorld;
    this.world = world;
    createMainPlayer();
  }

  public Character(
      TextureRegion textureRegion,
      int x,
      int y,
      int width,
      int height,
      float pixelToMeterRate,
      World world) {
    this(textureRegion, x, y, width, height, pixelToMeterRate, world, 0, 0);
  }

  public void createMainPlayer() {
    BodyDef bodyDef = setUpBodyDef();
    body = setUpBody(bodyDef);
    setUpFixture();
    body.createFixture(getFixtureDef()).setUserData(getUserData());
  }

  protected abstract Object getUserData();

  protected abstract BodyDef setUpBodyDef();

  /**
   * Should be able to make the body, and it should be able to make it multiple times so the body
   * needs to be deleted from the world, if needed.
   *
   * <p>*
   */
  protected abstract Body setUpBody(BodyDef bodyDef);

  protected abstract void setUpFixture();

  protected World getWorld() {
    return this.world;
  }

  public int getInitialXInTheWorld() {
    return initialXInTheWorld;
  }

  public int getInitialYInTheWorld() {
    return initialYInTheWorld;
  }
}
