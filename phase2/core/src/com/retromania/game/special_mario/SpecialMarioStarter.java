package com.retromania.game.special_mario;

import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.shared_abstractions.RetroManiaInnerGame;
import com.retromania.game.special_mario.screens.MenuScreen;
import com.retromania.game.special_mario.utils.GameRenderer;
import com.retromania.game.special_mario.utils.MusicManager;
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
  public void handleInput() {}

  @Override
  public void update() {
    worldLoader.getWorld().step(1 / 60f, 6, 2);
    handleInput();
  }

  @Override
  public void render(float delta) {
    update();
    mainWorldRenderer.render();
    menuScreen.render(delta);
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
    worldLoader = new WorldLoader(SpecialMarioConfiguration.getPixelToMeterConversionRate());
    mainWorldRenderer =
        new GameRenderer(
            worldLoader.getWorld(),
            worldLoader.getTiledMap(),
            worldLoader.getMainPlayer());
    menuScreen = new MenuScreen();
    menuScreen.show();
  }
}
