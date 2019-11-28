package com.retromania.game.special_mario.utils;

import com.badlogic.gdx.physics.box2d.World;

import javax.inject.Inject;
import javax.inject.Singleton;



@Singleton
public class WorldLoader {
  private World world;


  @Inject
  public WorldLoader(World world) {
    this.world = world;
    MusicManager.addSong("special_mario/marioFirstLevelMusic.ogg");
  }


  public World getWorld() {
    return world;
  }


}
