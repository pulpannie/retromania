package com.retromania.game.special_mario.utils;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;

public class TiledMapIndividualFactory {

  public static void getAllLayers(WorldLoader worldLoader) {
    getLayer(Layers.OBSTACLE, worldLoader);
    getLayer(Layers.FRIEZING_BLOCK, worldLoader);
    getLayer(Layers.REWARD, worldLoader);
    getLayer(Layers.WATER, worldLoader);
    getLayer(Layers.FIRE, worldLoader);
  }

  public static void getLayer(Layers l, WorldLoader worldLoader) {
    TiledMap tiledMap = worldLoader.getTiledMap();
    for (MapObject object :
        tiledMap.getLayers().get(l.getValue()).getObjects().getByType(RectangleMapObject.class)) {
      try {
        l.create(object, worldLoader);
      } catch (Exception e) {
        e.printStackTrace();
        break;
      }
    }
  }
}
