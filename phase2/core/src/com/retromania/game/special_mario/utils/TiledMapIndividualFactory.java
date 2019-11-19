package com.retromania.game.special_mario.utils;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;

public class TiledMapIndividualFactory {

  public static void getAllLayers(WorldInformation worldInformation) {
    getLayer(Layers.OBSTACLE, worldInformation);
    getLayer(Layers.FRIEZING_BLOCK, worldInformation);
    getLayer(Layers.REWARD, worldInformation);
    getLayer(Layers.WATER, worldInformation);
    getLayer(Layers.FIRE, worldInformation);
  }

  public static void getLayer(Layers l, WorldInformation worldInformation) {
    TiledMap tiledMap = worldInformation.getTiledMap();
    for (MapObject object :
        tiledMap.getLayers().get(l.getValue()).getObjects().getByType(RectangleMapObject.class)) {
      try {
        l.create(object, worldInformation);
      } catch (Exception e) {
        e.printStackTrace();
        break;
      }
    }
  }
}
