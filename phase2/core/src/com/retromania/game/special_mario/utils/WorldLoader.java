package com.retromania.game.special_mario.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.retromania.game.special_mario.individuals.MainPlayer;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.retromania.game.special_mario.SpecialMarioConfiguration.getPixelToMeterConversionRate;

@Singleton
public class WorldLoader {
  private World world;
  private TextureAtlas textureAtlas;
  private MainPlayer mainPlayer;


  @Inject
  public WorldLoader(World world){
    this(world,getPixelToMeterConversionRate());
  }

  public WorldLoader(World world, float pixelToMeterRate) {
    setUpTextureAtlas();
    setUpWorld(world);
    setUpMainPlayer(pixelToMeterRate);
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



  private void setUpWorld(World world) {
    this.world = world;
    this.world.setContactListener(new MarioWorldListener());
  }

  public MainPlayer getMainPlayer() {
    return mainPlayer;
  }

  public World getWorld() {
    return world;
  }


  private void setUpTextureAtlas() {
    textureAtlas = new TextureAtlas("special_mario/mario_small.pack");
  }

}
