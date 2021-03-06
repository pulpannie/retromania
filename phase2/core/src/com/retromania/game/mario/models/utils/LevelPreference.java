package com.retromania.game.mario.models.utils;

import com.badlogic.gdx.maps.tiled.TiledMap;

import java.util.Map;
import java.util.function.Supplier;

/**
 * This interface is gonna be used in order to create tile maps for different levels
 *
 * <p>*
 */
public interface LevelPreference {

  Map<Supplier<TiledMap>, String> getLevelModeFunctions();

  TiledMap setGameLevelToFirst();

  TiledMap setGameLevelToSecond();

  TiledMap getCurrentTileMap();

  boolean isItFirstMission();

  void reloadLevel();
}
