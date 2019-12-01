package com.retromania.game.special_mario.presenter;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;
import com.retromania.game.special_mario.models.map.MainPlayerInput;
import com.retromania.game.special_mario.models.player.MainPlayer;
import com.retromania.game.special_mario.models.utils.LevelPreference;

import javax.inject.Inject;

public abstract class MarioGamePresenter implements MarioGamePresentable {

  private MainPlayer mainPlayer;
  private World world;
  private LevelPreference levelPreference;

  public MarioGamePresenter(MainPlayer mainPlayer, World world, LevelPreference levelPreference) {
    this.mainPlayer = mainPlayer;
    this.world = world;
    this.levelPreference = levelPreference;
  }

  @Override
  public void present() {
    world.step(1 / 60f, 6, 2);
    mainPlayer.update();
  }

  @Override
  public TiledMap getTileMap() {
    return levelPreference.getCurrentTileMap();
  }

  public void setMainPlayerInput(MainPlayerInput mainPlayerInput) {
    mainPlayer.setInput(mainPlayerInput);
  }
}
