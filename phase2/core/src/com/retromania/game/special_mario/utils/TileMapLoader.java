package com.retromania.game.special_mario.utils;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.retromania.game.special_mario.utils.TiledMapIndividualFactory.bodies;

@Singleton
public class TileMapLoader {
  World world;

  private static final String FIRST_WORLD = "special_mario/firstLevel.tmx";
  private static final String SECOND_WORLD = "special_mario/PlayableOption1.tmx";
  private TiledMap tiledMap;

  @Inject
  public TileMapLoader(World world) {
    this.world = world;
    setUpFirstWorldTileMap();
  }

  private void setUpTiledMap(String mapAddress) {
    TmxMapLoader mapLoader = new TmxMapLoader();
    tiledMap = mapLoader.load(mapAddress);
    TiledMapIndividualFactory.getAllLayers(world,this);
  }

  public void setUpFirstWorldTileMap() {
    setUpTiledMap(FIRST_WORLD);
  }

  public void setUpSecondWorldTileMap() {
    for(Body b : bodies){
      world.destroyBody(b);
    }
    setUpTiledMap(SECOND_WORLD);
  }

  public TiledMap getTiledMap() {
    return tiledMap;
  }
}
