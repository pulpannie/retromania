package com.retromania.game.special_mario.models.utils;

import com.badlogic.gdx.maps.tiled.TiledMap;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton

public class LevelPreferenceMario implements LevelPreference {


    private Map<Supplier<TiledMap>, String> functionOfLevelMap = new HashMap<>();
    private TiledMapIndividualFactory tiledMapIndividualFactory;

    private TiledMap currentTile;


    @Inject
    LevelPreferenceMario(
            TiledMapIndividualFactory tiledMapIndividualFactory){
        this.tiledMapIndividualFactory = tiledMapIndividualFactory;

        functionOfLevelMap.put(this::setGameLevelToFirst, "FIRST LEVEL");
        functionOfLevelMap.put(this::setGameLevelToSecond, "SECOND LEVEL");

        this.currentTile = this.tiledMapIndividualFactory.getTiledMap();

    }

    @Override
    public Map<Supplier<TiledMap>, String> getLevelModeFunctions() {
        return functionOfLevelMap;
    }
    private TiledMap setUpTiledMap(){
        System.out.println("This was changed");
        this.currentTile = this.tiledMapIndividualFactory.getTiledMap();
        return currentTile;
    }
    @Override
    public TiledMap setGameLevelToFirst() {
        tiledMapIndividualFactory.setUpFirstWorldTileMap();
        return setUpTiledMap();
    }

    @Override
    public TiledMap setGameLevelToSecond() {
        tiledMapIndividualFactory.setUpSecondWorldTileMap();
        return setUpTiledMap();
    }

    @Override
    public TiledMap getCurrentTileMap() {
        return currentTile;
    }
}
