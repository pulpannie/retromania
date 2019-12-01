package com.retromania.game.special_mario.presenter;


import com.badlogic.gdx.maps.tiled.TiledMap;
import com.retromania.game.shared_abstractions.Presentable;

public interface MarioGamePresentable extends Presentable {
    TiledMap getTileMap();
    void reloadLevel();
}
