package com.retromania.game.special_mario.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.retromania.game.special_mario.individuals.MainPlayer;

import javax.inject.Inject;

import static com.retromania.game.special_mario.SpecialMarioConfiguration.getPixelToMeterConversionRate;

public class WorldLoader {
  private TiledMap tiledMap;
  private World world;
  private TextureAtlas textureAtlas;
  private MainPlayer mainPlayer;

  @Inject
  public WorldLoader(){
    this(getPixelToMeterConversionRate());
  }

  public WorldLoader(float pixelToMeterRate) {
    setUpTextureAtlas();
    setUpTiledMap();
    setUpWorld();
    setUpMainPlayer(pixelToMeterRate);
    TiledMapIndividualFactory.getAllLayers(this);
    MusicManager.addSong("special_mario/marioFirstLevelMusic.ogg");
  }

  private void setUpMainPlayer(float pixelToMeterRate) {
    TextureRegion marioTexture = textureAtlas.findRegion("mario_small");
    mainPlayer =
        new MainPlayer(
            marioTexture,
            0,
            0,
            marioTexture.getRegionWidth(),
            marioTexture.getRegionHeight(),
            pixelToMeterRate,
            getWorld(),
            32,
            256);
  }

  private void setUpTiledMap() {
    TmxMapLoader mapLoader = new TmxMapLoader();
    tiledMap = mapLoader.load("special_mario/firstLevel.tmx");
  }

  private void setUpWorld() {
    world = new World(new Vector2(0, -10), true);
    world.setContactListener(new MarioWorldListener());
  }

  public MainPlayer getMainPlayer() {
    return mainPlayer;
  }

  public World getWorld() {
    return world;
  }

  public TiledMap getTiledMap() {
    return tiledMap;
  }

  private void setUpTextureAtlas() {
    textureAtlas = new TextureAtlas("special_mario/mario_small.pack");
  }

}
