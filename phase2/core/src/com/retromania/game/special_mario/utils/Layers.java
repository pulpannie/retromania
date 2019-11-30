package com.retromania.game.special_mario.utils;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.physics.box2d.World;
import com.retromania.game.special_mario.abstractions.TiledMapIndividual;
import com.retromania.game.special_mario.models.Fire;
import com.retromania.game.special_mario.models.FriezingBlock;
import com.retromania.game.special_mario.models.Obstacle;
import com.retromania.game.special_mario.models.Reward;
import com.retromania.game.special_mario.models.Water;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;


/**
 *
 * This class is responsible for creating different types of TiledMapIndividual and
 * making sure that it has the same setup as the tmx file in our assets folder
 *
 * */
public enum Layers {

  OBSTACLE(2, Obstacle.class),
  FRIEZING_BLOCK(3, FriezingBlock.class),
  REWARD(4, Reward.class),
  WATER(5, Water.class),
  FIRE(6, Fire.class);

  private final int id;
  private Class c;

  Layers(int id, Class c) {
    this.id = id;
    this.c = c;
  }

  public int getValue() {
    return id;
  }

  public TiledMapIndividual create(MapObject m, World world)
      throws NoSuchMethodException, IllegalAccessException, InvocationTargetException,
          InstantiationException {


    Class cls[] = new Class[] {MapObject.class, World.class};
    Constructor constructor = c.getConstructor(cls);
    return (TiledMapIndividual)constructor.newInstance(m, world);


  }
}
