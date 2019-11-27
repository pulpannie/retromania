package com.retromania.game.special_mario;

import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.shared_abstractions.RetroManiaInnerGame;
import com.retromania.game.special_mario.screens.MenuScreen;
import com.retromania.game.special_mario.utils.GameRenderer;
import com.retromania.game.special_mario.utils.MusicManager;
import com.retromania.game.special_mario.utils.WorldInformation;

public class SpecialMarioStarter extends RetroManiaInnerGame {

  private static SpecialMarioStarter specialMarioStarter = new SpecialMarioStarter();
  private WorldInformation worldInformation;
  private GameRenderer renderer;
  private MenuScreen menuScreen = new MenuScreen();

  private SpecialMarioStarter() {
    super("MarioSpec", RetroManiaGame.Orientation.HORIZONTAL);
  }

  @Override
  public void handleInput() {
  }

  @Override
  public void update() {
    worldInformation.getWorld().step(1 / 60f, 6, 2);
    handleInput();
  }

  @Override
  public void render(float delta) {
    update();
    renderer.render();
    menuScreen.render(delta);
  }

  @Override
  public void resize(int width, int height) {
    renderer.resize(width, height);
  }

  public static SpecialMarioStarter getSpecialMarioStarter() {
    return specialMarioStarter;
  }
  public WorldInformation getWorldInformation() {
    return worldInformation;
  }

  @Override
  public void show() {
    worldInformation = new WorldInformation();
    renderer = new GameRenderer(worldInformation);
    MusicManager.addSong("special_mario/marioFirstLevelMusic.ogg");
    menuScreen.show();
  }


}
