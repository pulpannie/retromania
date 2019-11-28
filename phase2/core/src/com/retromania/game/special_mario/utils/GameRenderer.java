package com.retromania.game.special_mario.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.RetroMania;
import com.retromania.game.shared_abstractions.Renderable;
import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.special_mario.SpecialMarioConfiguration;
import com.retromania.game.special_mario.individuals.MainPlayer;

import javax.inject.Inject;

public class GameRenderer implements Renderable {

  private OrthogonalTiledMapRenderer orthogRenderer;
  private RetroManiaGame game = RetroMania.getRetroManiaInstance();
  private Box2DDebugRenderer b2ddr;
  private OrthographicCamera gamecam;


  private Viewport gamePort;
  private MainPlayer mainPlayer;
  private World world;
  private TileMapLoader tileMapLoader;

  @Inject
  public GameRenderer(MainPlayer mainPlayer,TileMapLoader tileMapLoader, World world) {
    this.tileMapLoader = tileMapLoader;
    this.world = world;
    this.mainPlayer = mainPlayer;
    setUpGamecam();
    setUpGamePort();
    setUpOrthogRenderer();
    b2ddr = new Box2DDebugRenderer();
  }

  private void setUpOrthogRenderer() {
    orthogRenderer =
        new OrthogonalTiledMapRenderer(
            tileMapLoader.getTiledMap(), SpecialMarioConfiguration.getPixelToMeterConversionRate());
  }

  private void clearPage(){
    setUpOrthogRenderer();
  }

  private void handleInput() {
    gamecam.position.x =
        gamePort.getWorldWidth() / 2 > mainPlayer.getX() ? gamecam.position.x : mainPlayer.getX();
  }

  public void update() {
    handleInput();
    gamecam.update();
  }

  @Override
  public void render(float delta) {
    update();
    clearDisplay();
    displayWorld();
    displayDebugLines();
    displayMainPlayer();
  }

  private void displayDebugLines() {
    b2ddr.render(world, gamecam.combined);
  }

  private void displayWorld() {
    setUpOrthogRenderer();
    orthogRenderer.setView(gamecam);
    orthogRenderer.render();
    game.sb.setProjectionMatrix(gamecam.combined);
  }

  private void displayMainPlayer() {
    game.sb.begin();
    mainPlayer.draw(game.sb);
    game.sb.end();
  }

  private void clearDisplay() {
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
  }

  public void resize(int width, int height) {
    gamePort.update(width, height);
  }

  private void setUpGamecam() {
    gamecam = new OrthographicCamera();
  }

  private void setUpGamePort() {
    gamePort =
        new FitViewport(
            SpecialMarioConfiguration.convertPixelToMeter(RetroManiaGame.V_WIDTH),
            SpecialMarioConfiguration.convertPixelToMeter(RetroManiaGame.V_HEIGHT),
            gamecam);
    gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
  }

  public Viewport getGamePort() {
    return gamePort;
  }
}
