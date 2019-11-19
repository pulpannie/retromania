package com.retromania.game.special_mario;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.retromania.game.shared_abstractions.Configuration;
import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.shared_abstractions.RetroManiaGeneralUser;
import com.retromania.game.shared_abstractions.RetroManiaInnerGame;
import com.retromania.game.shared_abstractions.User;
import com.retromania.game.special_mario.utils.GameRenderer;
import com.retromania.game.special_mario.utils.WorldInformation;

import java.util.List;

public class SpecialMarioStarter extends RetroManiaInnerGame {

  private static SpecialMarioStarter specialMarioStarter = new SpecialMarioStarter();
  private WorldInformation worldInformation;
  private GameRenderer renderer;
  private OrthographicCamera gamecam;

  private SpecialMarioStarter() {
    super("MarioSpec", RetroManiaGame.Orientation.HORIZONTAL);
    setUpMusic();
  }

  @Override
  public void setCurrentUser(String name) {
    Preferences preferences = game.getPrefrences("Mario");
    this.currentUser = new RetroManiaGeneralUser(name);
    this.currentUser.setScore(preferences.getInteger(currentUser.getUserName()));
  }

  @Override
  public void setBestUser() {
    Preferences preferences = game.getPrefrences("Mario");
    User user = new RetroManiaGeneralUser(preferences.getString(Configuration.bestUserUserName));
    user.setScore(preferences.getInteger(Configuration.bestUserScore));
    bestUser = user;
  }

  @Override
  public Integer getBestUserScore() {
    return bestUser.getScore();
  }

  @Override
  public void save(Object... args) {}

  @Override
  public List<Object> retrieve() {
    return null;
  }

  @Override
  public void handleInput() {
    gamecam.position.x =
        gamePort.getWorldWidth() / 2 > worldInformation.getMainPlayer().getX()
            ? gamecam.position.x
            : worldInformation.getMainPlayer().getX();
  }

  @Override
  public void show() {
    worldInformation = new WorldInformation();

    gamecam = new OrthographicCamera();
    gamePort =
        new FitViewport(
            SpecialMarioConfiguration.convertPixelToMeter(RetroManiaGame.V_WIDTH),
            SpecialMarioConfiguration.convertPixelToMeter(RetroManiaGame.V_HEIGHT),
            gamecam);
    gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
    renderer = new GameRenderer(worldInformation, gamecam);
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
  }

  @Override
  public void resize(int width, int height) {
    gamePort.update(width, height);
  }


  public static SpecialMarioStarter getSpecialMarioStarter() {
    return specialMarioStarter;
  }

  void setUpMusic() {
    AssetManager assetManager = new AssetManager();
    assetManager.load("special_mario/marioFirstLevelMusic.ogg", Music.class);
    assetManager.finishLoading();
    Music music = assetManager.get("special_mario/marioFirstLevelMusic.ogg");
    music.setLooping(true);
    music.play();
  }

  public WorldInformation getWorldInformation() {
    return worldInformation;
  }
}
