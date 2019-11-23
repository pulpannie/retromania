package com.retromania.game.special_mario;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
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
  private OrthographicCamera gamecam;
  private MenuScreen menuScreen = new MenuScreen();

  private SpecialMarioStarter() {
    super("MarioSpec", RetroManiaGame.Orientation.HORIZONTAL);
  }

  @Override
  public void handleInput() {
    gamecam.position.x =
        gamePort.getWorldWidth() / 2 > worldInformation.getMainPlayer().getX()
            ? gamecam.position.x
            : worldInformation.getMainPlayer().getX();
  }

  @Override
  public void update() {
    gamecam.update();
    worldInformation.getWorld().step(1 / 60f, 6, 2);
    worldInformation.getMainPlayer().update();
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
    gamePort.update(width, height);
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
    setUpGamecam();
    setUpGamePort();
    renderer = new GameRenderer(worldInformation, gamecam);
    MusicManager.addSong("special_mario/marioFirstLevelMusic.ogg");
    menuScreen.show();
  }
  private void setUpGamePort() {
    gamePort =
            new FitViewport(
                    SpecialMarioConfiguration.convertPixelToMeter(RetroManiaGame.V_WIDTH),
                    SpecialMarioConfiguration.convertPixelToMeter(RetroManiaGame.V_HEIGHT),
                    gamecam);
    gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
  }
  private void setUpGamecam(){
    gamecam = new OrthographicCamera();
  }


}
