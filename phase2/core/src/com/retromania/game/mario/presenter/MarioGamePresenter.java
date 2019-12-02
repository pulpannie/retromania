package com.retromania.game.mario.presenter;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;
import com.retromania.game.mario.abstractions.DeathObserver;
import com.retromania.game.mario.abstractions.FinisherObserver;
import com.retromania.game.mario.models.player.MainPlayerInput;
import com.retromania.game.mario.models.player.MainPlayer;
import com.retromania.game.mario.models.utils.LevelPreference;
import com.retromania.game.mario.utils.MarioWorldListener;

import java.util.ArrayList;
import java.util.List;

public abstract class   MarioGamePresenter implements MarioGamePresentable {

  MainPlayer mainPlayer;
  private World world;
  private LevelPreference levelPreference;

  private List<DeathObserver> deathObservers = new ArrayList<>();
  private List<FinisherObserver> finishObservers = new ArrayList<>();

  public MarioGamePresenter(
      MainPlayer mainPlayer,
      World world,
      LevelPreference levelPreference,
      MarioWorldListener marioWorldListener) {
    this.mainPlayer = mainPlayer;
    this.world = world;
    this.levelPreference = levelPreference;
    world.setContactListener(marioWorldListener);
  }

  @Override
  public void present() {
    world.step(1 / 60f, 6, 2);
    mainPlayer.update();
    if (mainPlayer.isFinished()) {
      updateFinisherObservers();
      resetPlayer();
    }
    if (mainPlayer.isDead()) {
      updateDeathObservers();
      resetPlayer();
    }
  }

  @Override
  public TiledMap getTileMap() {
    return levelPreference.getCurrentTileMap();
  }

  @Override
  public void setMainPlayerInput(MainPlayerInput mainPlayerInput) {
    mainPlayer.setInput(mainPlayerInput);
  }

  @Override
  public void reloadLevel() {
    levelPreference.reloadLevel();
  }

  @Override
  public void updateDeathObservers() {
    for (DeathObserver d : deathObservers) {
      d.deathSeen();
    }
  }

  @Override
  public void addDeathObserver(DeathObserver deathObserver) {
    deathObservers.add(deathObserver);
  }

  @Override
  public void updateFinisherObservers() {
    for (FinisherObserver f : finishObservers) {
      f.finishSeen();
    }
  }

  @Override
  public void addFinisherObserver(FinisherObserver finisherObserver) {
    finishObservers.add(finisherObserver);
  }

  @Override
  public void createPlayerFromScratch() {
    mainPlayer.createMainPlayer();
  }

  @Override
  public float getXMainPlayer() {
    return mainPlayer.getX();
  }

  @Override
  public float getYMainPlayer() {
    return mainPlayer.getY();
  }

  @Override
  public void letMainPlayerShow(SpriteBatch sb) {
    mainPlayer.draw(sb);
  }

  @Override
  public void resetPlayer() {
    mainPlayer.setFinished(false);
    mainPlayer.setDead(false);
    mainPlayer.createMainPlayer();
  }

  @Override
  public void playerRanOver() {
    mainPlayer.setDead(true);
  }

  @Override
  public float getPlayerWidth() {
    return 32 / 100f;
  }

  @Override
  public float getPlayerDefaultX() {
    return mainPlayer.getInitialXInTheWorld();
  }
}
