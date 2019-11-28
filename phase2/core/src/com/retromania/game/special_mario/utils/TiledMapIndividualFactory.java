package com.retromania.game.special_mario.utils;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.retromania.game.special_mario.abstractions.TiledMapIndividual;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

public class TiledMapIndividualFactory {


  public static List<Body> bodies= new ArrayList<>();

  public static void getAllLayers(World world, TileMapLoader tileMapLoader) {
    bodies.clear();
    getLayer(Layers.OBSTACLE, world,tileMapLoader);
    getLayer(Layers.FRIEZING_BLOCK, world, tileMapLoader);
    getLayer(Layers.REWARD, world, tileMapLoader);
    getLayer(Layers.WATER, world, tileMapLoader);
    getLayer(Layers.FIRE, world, tileMapLoader);
  }

  public static void getLayer(Layers l,World world, TileMapLoader tileMapLoader) {
    TiledMap tiledMap = tileMapLoader.getTiledMap();
    for (MapObject object :
        tiledMap.getLayers().get(l.getValue()).getObjects().getByType(RectangleMapObject.class)) {
      try {
        TiledMapIndividual ds = l.create(object, world);
        bodies.add(ds.getBody());
      } catch (Exception e) {
        e.printStackTrace();
        break;
      }
    }
  }
}
