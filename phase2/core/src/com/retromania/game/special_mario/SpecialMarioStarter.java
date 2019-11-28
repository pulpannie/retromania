package com.retromania.game.special_mario;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;
import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.shared_abstractions.RetroManiaInnerGame;
import com.retromania.game.special_mario.individuals.MainPlayer;
import com.retromania.game.special_mario.individuals.MainPlayerInput;
import com.retromania.game.special_mario.screens.MenuScreen;
import com.retromania.game.special_mario.utils.GameRenderer;
import com.retromania.game.special_mario.utils.MusicManager;

import javax.inject.Inject;

public class SpecialMarioStarter extends RetroManiaInnerGame {

  private GameRenderer mainWorldRenderer;
  private MenuScreen menuScreen;
  private World world;
  private MainPlayer mainPlayer;
  private MusicManager musicManager;
  @Inject
  public SpecialMarioStarter(
      MainPlayer mainPlayer,
      World world,
      GameRenderer mainWorldRenderer,
      MenuScreen menuScreen,
      MusicManager musicManager) {
    super("MarioSpec", RetroManiaGame.Orientation.HORIZONTAL);
    this.mainPlayer = mainPlayer;
    this.world = world;
    setUpWorld();
    setUpMainWorldRenderer(mainWorldRenderer);
    setUpMenuScreen(menuScreen);
    this.musicManager = musicManager;
  }

  private void setUpWorld() {
    addModel(mainPlayer);
  }

  @Override
  public void handleInput() {
    setUpMainPlayerInput();
  }

  @Override
  public void update() {
    handleInput();
    world.step(1 / 60f, 6, 2);
    updateModels();
  }

  private void setUpMainPlayerInput() {
    mainPlayer.setInput(
        new MainPlayerInput(
            mainWorldRenderer.getGamePort().getScreenWidth(),
            mainWorldRenderer.getGamePort().getScreenHeight(),
            Gdx.input.getX(),
            Gdx.input.getY(),
            Gdx.input.justTouched(),
            Gdx.input.isTouched()));
  }

  @Override
  public void resize(int width, int height) {
    mainWorldRenderer.resize(width, height);
  }

  @Override
  public void show() {
    this.musicManager.play();
  }

  private void setUpMenuScreen(MenuScreen menuScreen) {
    this.menuScreen = menuScreen;
    addRenderable(this.menuScreen);
    this.menuScreen.show();
  }

  private void setUpMainWorldRenderer(GameRenderer mainWorldRenderer) {
    this.mainWorldRenderer = mainWorldRenderer;
    addRenderable(this.mainWorldRenderer);
  }
}
