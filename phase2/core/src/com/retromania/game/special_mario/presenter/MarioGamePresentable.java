package com.retromania.game.special_mario.presenter;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.retromania.game.shared_abstractions.Presentable;
import com.retromania.game.special_mario.abstractions.DeathObservable;
import com.retromania.game.special_mario.abstractions.FinisherObservable;
import com.retromania.game.special_mario.models.player.MainPlayerInput;

public interface MarioGamePresentable extends Presentable, FinisherObservable, DeathObservable {
    TiledMap getTileMap();
    void reloadLevel();
    void setMainPlayerInput(MainPlayerInput mainPlayerInput);
    void createPlayerFromScratch();
    float getXMainPlayer();
    float getYMainPlayer();
    void letMainPlayerShow(SpriteBatch sb);
}
