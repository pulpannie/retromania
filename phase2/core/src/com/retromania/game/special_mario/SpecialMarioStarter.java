package com.retromania.game.special_mario;

import com.badlogic.gdx.Gdx;
import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.shared_abstractions.RetroManiaInnerGame;
import com.retromania.game.special_mario.individuals.MainPlayerInput;
import com.retromania.game.special_mario.screens.MenuScreen;
import com.retromania.game.special_mario.utils.GameRenderer;
import com.retromania.game.special_mario.utils.WorldLoader;

public class SpecialMarioStarter extends RetroManiaInnerGame {

  private static SpecialMarioStarter specialMarioStarter = new SpecialMarioStarter();
  private WorldLoader worldLoader;
  private GameRenderer mainWorldRenderer;
  private MenuScreen menuScreen;

  private SpecialMarioStarter() {
    super("MarioSpec", RetroManiaGame.Orientation.HORIZONTAL);
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
    worldLoader.getMainPlayer().setInput(
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

  public static SpecialMarioStarter getSpecialMarioStarter() {
    return specialMarioStarter;
  }

  @Override
  public void show() {
    setUpWorldLoader();
    setUpMainWorldRenderer();
    setUpMenuScreen();
  }

  private void setUpMenuScreen() {
    menuScreen = new MenuScreen();
    addRenderable(menuScreen);
    menuScreen.show();
  }

  private void setUpMainWorldRenderer() {
    mainWorldRenderer =
        new GameRenderer(
            worldLoader.getWorld(),
            worldLoader.getTiledMap(),
            worldLoader.getMainPlayer());
    addRenderable(mainWorldRenderer);
  }

  private void setUpWorldLoader() {
    worldLoader = new WorldLoader(SpecialMarioConfiguration.getPixelToMeterConversionRate());
    addModel(worldLoader.getMainPlayer());
  }
}
