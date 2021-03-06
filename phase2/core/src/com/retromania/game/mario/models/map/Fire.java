package com.retromania.game.mario.models.map;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.physics.box2d.World;
import com.retromania.game.mario.abstractions.TiledMapIndividual;
import com.retromania.game.mario.models.player.MainPlayer;
import com.retromania.game.mario.models.player.MainPlayerCollisionInfo;
/**
 * The logic and Tiles for see of fire
 *
 * <p>*
 */
public class Fire extends TiledMapIndividual {

  public Fire(MapObject object, World world) {
    super(object, world);
  }

  @Override
  public void hitWithPlayer(MainPlayerCollisionInfo playerCollisionInfo) {
    setCategoryMask((short) 0);
    setCollidableWith((short) 0);
  }

  @Override
  public void hitWithBodyOfMainPlayer(MainPlayer mainPlayer) {
    mainPlayer.setDead(true);
  }

  @Override
  public void update(Object... args) {}
}
