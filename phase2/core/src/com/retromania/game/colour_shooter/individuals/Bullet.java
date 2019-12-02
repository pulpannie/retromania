package com.retromania.game.colour_shooter.individuals;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.retromania.game.shared_abstractions.Character;
import com.retromania.game.shared_abstractions.Collidable;
import com.retromania.game.shared_abstractions.RetroManiaModel;

/**
 * THIS CLASS IS RESPONSIBLE FOR MAKING THE BULLET. IT has the various features of shooting the
 * bullet, collision.
 */
public class Bullet extends Character implements RetroManiaModel, Collidable {

  private boolean isFinished = false;
  private float angleSquare;

  private static int getRadius() {
    return 3;
  }

  /**
   * @param given_world:   the world
   * @param textureRegion: our texture region of the bullet
   * @param x:             position x
   * @param y:             position y
   */
  public Bullet(World given_world, TextureRegion textureRegion, int x, int y) {
    super(textureRegion, x, y, getRadius() * 4, getRadius() * 4, 1f, given_world, x, y);
  }

  /**
   * @param isFinished: if bullet collides
   * @param angle:      the angle of the square
   */
  public void setBulletCollided(boolean isFinished, float angle) {
    this.isFinished = isFinished;
    this.angleSquare = (float) Math.toDegrees(angle) % 360;
  }

  public float getAngleFinished() {
    return angleSquare;
  }

  public boolean getIsFinished() {
    return isFinished;
  }

  @Override
  protected Object getUserData() {
    return this;
  }

  /**
   * @return: setup the body, make the Dynamic Body Setup the rotation, create the body definition.
   */
  @Override
  protected BodyDef setUpBodyDef() {
    BodyDef bDef = new BodyDef();
    bDef.position.set(getInitialXInTheWorld(), getInitialYInTheWorld());
    bDef.fixedRotation = true;
    bDef.type = BodyDef.BodyType.DynamicBody;
    return bDef;
  }

  /**
   * @param bDef: creating a body definition
   * @return: then return the body
   */
  @Override
  protected Body setUpBody(BodyDef bDef) {
    this.body = getWorld().createBody(bDef);
    return body;
  }

  /**
   * Setting up the fixture, which is the polygon shape.
   */
  @Override
  protected void setUpFixture() {

    PolygonShape shape = new PolygonShape();
    shape.setAsBox(getRadius() * 2, getRadius() * 2);
    fixtureDef = new FixtureDef();

    setDefaultCategoryMask();
    setDefaultCollidableWith();

    fixtureDef.shape = shape;
  }

  /**
   * returning type of FixtureDef
   */
  @Override
  public FixtureDef getFixtureDef() {
    return fixtureDef;
  }

  /**
   * Necessary commands required
   * for setting up Body in physics engine using the
   * LibGDX library.
   */
  @Override
  public short getDefaultMask() {
    return -1;
  }

  @Override
  public short getDefaultTarget() {
    return -1;
  }

  /**
   * @param speed_x: speed of bullet in x-direction
   * @param speed_y: speed of bullet in y-direction.
   */
  public void move(float speed_x, float speed_y) {
    this.body.setLinearVelocity(speed_x, speed_y);
  }

  /**
   * Updating the position of the bullet.
   */
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
