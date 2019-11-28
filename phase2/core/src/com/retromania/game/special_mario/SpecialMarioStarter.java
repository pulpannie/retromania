package com.retromania.game.special_mario;

import com.badlogic.gdx.Gdx;
import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.shared_abstractions.RetroManiaInnerGame;
import com.retromania.game.special_mario.individuals.MainPlayerInput;
import com.retromania.game.special_mario.screens.MenuScreen;
import com.retromania.game.special_mario.utils.GameRenderer;
import com.retromania.game.special_mario.utils.WorldLoader;

import javax.inject.Inject;

public class SpecialMarioStarter extends RetroManiaInnerGame {

  private WorldLoader worldLoader;
  private GameRenderer mainWorldRenderer;
  private MenuScreen menuScreen;

  @Inject
  public SpecialMarioStarter(
      WorldLoader worldLoader, GameRenderer mainWorldRenderer, MenuScreen menuScreen) {
    super("MarioSpec", RetroManiaGame.Orientation.HORIZONTAL);
    setUpWorld(worldLoader);
    setUpMainWorldRenderer(mainWorldRenderer);
    setUpMenuScreen(menuScreen);
  }

  private void setUpWorld(WorldLoader worldLoader) {
    this.worldLoader = worldLoader;
    addModel(worldLoader.getMainPlayer());
  }

  @Override
  public void handleInput() {
    setUpMainPlayerInput();
  }

  @Override
  public void update() {
    handleInput();
    worldLoader.getWorld().step(1 / 60f, 6, 2);
    updateModels();
  }

  private void setUpMainPlayerInput() {
    worldLoader
        .getMainPlayer()
        .setInput(
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
  public void show() {}

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
