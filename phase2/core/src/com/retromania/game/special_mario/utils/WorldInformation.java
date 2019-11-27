package com.retromania.game.special_mario.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.retromania.game.special_mario.individuals.MainPlayer;

public class WorldInformation {
  private TiledMap tiledMap;
  private World world;
  private TextureAtlas textureAtlas = new TextureAtlas("special_mario/mario_small.pack");
  private MainPlayer mainPlayer;
  public WorldInformation() {
    setUpTiledMap();
    setUpWorld();
    mainPlayer = new MainPlayer(this);
    TiledMapIndividualFactory.getAllLayers(this);
  }
  private void setUpTiledMap() {
    TmxMapLoader mapLoader = new TmxMapLoader();
    tiledMap = mapLoader.load("special_mario/firstLevel.tmx");
  }
  private void setUpWorld() {
    world = new World(new Vector2(0, -10), true);
    world.setContactListener(new MarioWorldListener());
  }
  MainPlayer getMainPlayer() {
    return mainPlayer;
  }
  public World getWorld() {
    return world;
  }
  TiledMap getTiledMap() {
    return tiledMap;
  }
  public TextureAtlas getTextureAtlas() {
    return textureAtlas;
  }
}
