package com.retromania.game.special_mario;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.retromania.game.shared_abstractions.Configuration;
import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.shared_abstractions.RetroManiaGeneralUser;
import com.retromania.game.shared_abstractions.RetroManiaInnerGame;
import com.retromania.game.shared_abstractions.User;
import com.retromania.game.special_mario.individuals.MainPlayer;
import com.retromania.game.special_mario.utils.MarioWorldListener;
import com.retromania.game.special_mario.utils.TiledMapIndividualFactory;
import com.retromania.game.special_mario.utils.WorldInformation;

import java.util.List;

public class SpecialMarioStarter extends RetroManiaInnerGame {




  private OrthogonalTiledMapRenderer renderer;
  private OrthographicCamera gamecam;
  private AssetManager assetManager;
  private Box2DDebugRenderer b2ddr;




  public WorldInformation getWorldInformation() {
    return worldInformation;
  }

  private WorldInformation worldInformation ;


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
    gamecam.position.x = gamePort.getWorldWidth()/2 > worldInformation.getMainPlayer().getX() ?
            gamecam.position.x : worldInformation.getMainPlayer().getX();
  }

  @Override
  public void show() {
    worldInformation = new WorldInformation();
    b2ddr = new Box2DDebugRenderer();

    gamecam = new OrthographicCamera();
    gamePort =
            new FitViewport(
                    SpecialMarioConfiguration.convertPixelToMeter(RetroManiaGame.V_WIDTH),
                    SpecialMarioConfiguration.convertPixelToMeter(RetroManiaGame.V_HEIGHT), gamecam);

    renderer = new OrthogonalTiledMapRenderer(worldInformation.getTiledMap(), SpecialMarioConfiguration.getPixelToMeterConversionRate());
    gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
  }

  @Override
  public void update() {
    gamecam.update();
    worldInformation.getWorld().step(1 / 60f, 6, 2);
    renderer.setView(gamecam);
    worldInformation.getMainPlayer().update();
    handleInput();
  }

  @Override
  public void render(float delta) {
    update();
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    renderer.render();
    b2ddr.render(worldInformation.getWorld(), gamecam.combined);
    game.sb.setProjectionMatrix(gamecam.combined);
    game.sb.begin();
    worldInformation.getMainPlayer().draw(game.sb);
    game.sb.end();
  }


  private static SpecialMarioStarter specialMarioStarter = new SpecialMarioStarter();






  @Override
  public void resize(int width, int height) {
    gamePort.update(width, height);
  }

  @Override
  public void pause() {}

  @Override
  public void resume() {}

  @Override
  public void hide() {}

  @Override
  public void dispose() {}

  public static SpecialMarioStarter getSpecialMarioStarter() {
    return specialMarioStarter;
  }

  void setUpMusic(){

    assetManager = new AssetManager();
    assetManager.load("special_mario/marioFirstLevelMusic.ogg", Music.class);
    assetManager.finishLoading();
    Music music =    assetManager.get("special_mario/marioFirstLevelMusic.ogg");
    music.setLooping(true);
    music.play();
  }
}
