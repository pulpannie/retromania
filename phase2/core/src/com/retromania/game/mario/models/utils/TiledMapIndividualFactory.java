package com.retromania.game.mario.models.utils;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.retromania.game.mario.abstractions.TiledMapIndividual;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * This class is responsible to use layers to manage bodies inside the map (Everything but the
 * characters) and to be able to change those levels and delete the previous level
 *
 * <p>*
 */
@Singleton
class TiledMapIndividualFactory {

  private static final String FIRST_WORLD = "special_mario/firstLevel.tmx";
  private static final String SECOND_WORLD = "special_mario/PlayableOption1.tmx";

  private TiledMap tiledMap;

  private World world;

  private String currentLevel;

  void reloadLevel() {
    clearBodiesOfMap();
    setUpTiledMap(currentLevel);
  }

  @Inject
  TiledMapIndividualFactory(World world) {
    this.world = world;
    setUpFirstWorldTileMap();
  }

  private void setUpTiledMap(String mapAddress) {
    currentLevel = mapAddress;
    TmxMapLoader mapLoader = new TmxMapLoader();
    tiledMap = mapLoader.load(mapAddress);
    getAllLayers();
  }

  void setUpFirstWorldTileMap() {
    clearBodiesOfMap();
    setUpTiledMap(FIRST_WORLD);
  }

  void setUpSecondWorldTileMap() {
    clearBodiesOfMap();
    setUpTiledMap(SECOND_WORLD);
  }

  private void clearBodiesOfMap() {
    for (Body b : bodies) {
      world.destroyBody(b);
    }
    bodies.clear();
  }

  private List<Body> bodies = new ArrayList<>();

  private void getAllLayers() {
    getLayer(Layers.OBSTACLE, world);
    getLayer(Layers.FRIEZING_BLOCK, world);
    getLayer(Layers.REWARD, world);
    getLayer(Layers.WATER, world);
    getLayer(Layers.FIRE, world);
    getLayer(Layers.GAME_FINISHER, world);
  }

  TiledMap getTiledMap() {
    return tiledMap;
  }

  private void getLayer(Layers l, World world) {
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
