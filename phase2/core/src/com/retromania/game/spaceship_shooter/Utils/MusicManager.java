package com.retromania.game.spaceship_shooter.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

/**
 * class that controls music of the game
 *
 * @author Umid, Thuy
 */
public class MusicManager {
  /** instance of music */
  private static Music music =
      Gdx.audio.newMusic(Gdx.files.internal("spaceship_shooter/shooting_star_music.mp3"));

  /** Constructor of MusicManager */
  public MusicManager() {}

  /** pauses music */
  public static void pause() {
    music.pause();
  }

  /** plays music */
  public static void play() {
    music.play();
  }

  /** stops music */
  public static void stop() {
    music.stop();
  }
}
