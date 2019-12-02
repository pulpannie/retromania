package com.retromania.game.mario.models.map;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.physics.box2d.World;
import com.retromania.game.mario.abstractions.TiledMapIndividual;
import com.retromania.game.mario.models.player.MainPlayer;
import com.retromania.game.mario.models.player.MainPlayerCollisionInfo;

/**
 * The tile and logic for obstacles that the main player can stand on
 *
 * <p>*
 */
public class Obstacle extends TiledMapIndividual {
  public Obstacle(MapObject object, World world) {
    super(object, world);
  }

  @Override
  public void hitWithPlayer(MainPlayerCollisionInfo playerCollisionInfo) {}

  @Override
  public void hitWithBodyOfMainPlayer(MainPlayer mainPlayer) {}

  @Override
  public void update(Object... args) {}
}
