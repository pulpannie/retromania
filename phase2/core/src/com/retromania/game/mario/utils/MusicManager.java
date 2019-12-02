package com.retromania.game.mario.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;

import javax.inject.Inject;
import javax.inject.Named;

public class MusicManager {
  private Music music;

  @Inject
  public MusicManager(@Named("Music Name") String musicName) {
    AssetManager assetManager = new AssetManager();
    assetManager.load(musicName, Music.class);
    assetManager.finishLoading();
    music = assetManager.get(musicName);
    music.setLooping(true);
  }

  public void play() {
    music.play();
  }
}
