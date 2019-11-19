package com.retromania.game.special_mario.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;

public class MusicManager {

    private static AssetManager assetManager = new AssetManager();
    public static void addSong(String address){
        assetManager.load(address, Music.class);
        assetManager.finishLoading();
        Music music = assetManager.get(address);
        music.setLooping(true);
        music.play();
    }

}
