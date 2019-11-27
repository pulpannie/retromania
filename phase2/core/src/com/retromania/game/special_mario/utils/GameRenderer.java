package com.retromania.game.special_mario.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.RetroMania;
import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.special_mario.SpecialMarioConfiguration;

public class GameRenderer {

  private OrthogonalTiledMapRenderer orthogRenderer;
  private WorldInformation worldInformation;
  private RetroManiaGame game = RetroMania.getRetroManiaInstance();
  private Box2DDebugRenderer b2ddr;
  private OrthographicCamera gamecam;
  private Viewport gamePort;

  public GameRenderer(WorldInformation worldInformation) {
    setUpGamecam();
    setUpGamePort();
    this.worldInformation = worldInformation;
    setUpOrthogRenderer(worldInformation);
    b2ddr = new Box2DDebugRenderer();
  }

  private void setUpOrthogRenderer(WorldInformation worldInformation) {
    orthogRenderer =
        new OrthogonalTiledMapRenderer(
            worldInformation.getTiledMap(),
            SpecialMarioConfiguration.getPixelToMeterConversionRate());
  }

  private void handleInput() {
    gamecam.position.x =
        gamePort.getWorldWidth() / 2 > worldInformation.getMainPlayer().getX()
            ? gamecam.position.x
            : worldInformation.getMainPlayer().getX();
  }

  public void update() {
    handleInput();
    gamecam.update();
  }

  public void render() {
    update();
    clearDisplay();
    displayWorld();
    displayDebugLines();
    displayMainPlayer();
  }

  private void displayDebugLines() {
    b2ddr.render(worldInformation.getWorld(), gamecam.combined);
  }

  private void displayWorld() {
    orthogRenderer.setView(gamecam);
    orthogRenderer.render();
    game.sb.setProjectionMatrix(gamecam.combined);
  }

  private void displayMainPlayer() {
    worldInformation
        .getMainPlayer()
        .update(
            gamePort.getScreenWidth(),
            gamePort.getScreenHeight(),
            Gdx.input.getX(),
            Gdx.input.getY(),
            Gdx.input.justTouched(),
            Gdx.input.isTouched());
    game.sb.begin();
    worldInformation.getMainPlayer().draw(game.sb);
    game.sb.end();
  }

  private void clearDisplay() {
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
  }

  public OrthogonalTiledMapRenderer getOrthogRenderer() {
    return orthogRenderer;
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
}
