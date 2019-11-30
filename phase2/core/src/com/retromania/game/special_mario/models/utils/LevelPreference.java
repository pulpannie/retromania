package com.retromania.game.special_mario.models.utils;

import com.badlogic.gdx.maps.tiled.TiledMap;

import java.util.Map;
import java.util.function.Supplier;

public interface LevelPreference {

    Map<Supplier<TiledMap>, String> getLevelModeFunctions();

    TiledMap setGameLevelToFirst();

    TiledMap setGameLevelToSecond();

    TiledMap getCurrentTileMap();


}
