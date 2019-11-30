package com.retromania.game.spaceship_shooter.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MusicManager {
    static Music music = Gdx.audio.newMusic(Gdx.files.internal("spaceship_shooter/shooting_star_music.mp3"));
    public MusicManager(){
    }
    public static void pause(){music.pause();}
    public static void play(){music.play();}
    public static void stop(){music.stop();}
}
