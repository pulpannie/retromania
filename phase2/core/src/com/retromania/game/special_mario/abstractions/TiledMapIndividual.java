package com.retromania.game.special_mario.abstractions;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.retromania.game.shared_abstractions.Collidable;
import com.retromania.game.shared_abstractions.Individual;
import com.retromania.game.special_mario.SpecialMarioStarter;
import com.retromania.game.special_mario.utils.MainPlayerCollisionInfo;
import com.retromania.game.special_mario.utils.WorldLoader;

import static com.retromania.game.special_mario.SpecialMarioConfiguration.convertPixelToMeter;

public abstract class TiledMapIndividual implements Individual, Collidable {

  private Rectangle rectangleBound;
  private Body body;
  private FixtureDef fixtureDef;

  public TiledMapIndividual(MapObject object, WorldLoader worldLoader) {
    SpecialMarioStarter innerGame = SpecialMarioStarter.getSpecialMarioStarter();
    rectangleBound = setUpBound(object);
    body = setUpBodyDef(innerGame, worldLoader);
    PolygonShape shape = setUpShape();
    Fixture fixture = createFixture(shape);
    fixture.setUserData(this);
  }

  private Fixture createFixture(PolygonShape shape) {
    fixtureDef = new FixtureDef();
    setDefaultCategoryMask();
    fixtureDef.shape = shape;
    return body.createFixture(fixtureDef);
  }

  private PolygonShape setUpShape() {
    PolygonShape shape = new PolygonShape();
    shape.setAsBox(
        convertPixelToMeter(rectangleBound.getWidth() / 2), convertPixelToMeter(rectangleBound.getHeight() / 2));
    return shape;
  }

  private Rectangle setUpBound(MapObject object) {
    return ((RectangleMapObject) object).getRectangle();
  }

  private Body setUpBodyDef(SpecialMarioStarter innerGame, WorldLoader worldLoader) {
    BodyDef bodyDef = new BodyDef();
    bodyDef.type = BodyDef.BodyType.StaticBody;
    float x = rectangleBound.getX() + rectangleBound.getWidth() / 2;
    float y = rectangleBound.getY() + rectangleBound.getHeight() / 2;
    bodyDef.position.set(convertPixelToMeter(x), convertPixelToMeter(y));
    return worldLoader.getWorld().createBody(bodyDef);
  }

  @Override
  public FixtureDef getFixtureDef() {
    return fixtureDef;
  }

  @Override
  public short getDefaultMask() {
    return 4;
  }

  @Override
  public short getDefaultTarget() {
    return -1;
  }

  public abstract void hitWithPlayer(MainPlayerCollisionInfo playerCollisionInfo);
}
