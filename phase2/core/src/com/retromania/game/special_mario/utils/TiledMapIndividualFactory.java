package com.retromania.game.special_mario.utils;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.retromania.game.special_mario.SpecialMarioStarter;

public class TiledMapIndividualFactory {

  public static void getAllLayers() {
    getLayer(Layers.OBSTACLE);
    getLayer(Layers.FRIZING_BLOCK);
    getLayer(Layers.REWARD);
    getLayer(Layers.WATER);
    getLayer(Layers.FIRE);
  }

  public static void getLayer(Layers l) {
    SpecialMarioStarter innerGame = SpecialMarioStarter.getSpecialMarioStarter();
    TiledMap tiledMap = innerGame.getTiledMap();
    for (MapObject object :
        tiledMap.getLayers().get(l.getValue()).getObjects().getByType(RectangleMapObject.class)) {
      try {
        l.create(object);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
