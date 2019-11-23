package com.retromania.game.special_mario.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.retromania.game.RetroMania;
import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.special_mario.SpecialMarioConfiguration;

public class GameRenderer {

  private OrthogonalTiledMapRenderer orthogRenderer;
  private WorldInformation worldInformation;
  private RetroManiaGame game = RetroMania.getRetroManiaInstance();
  private Box2DDebugRenderer b2ddr;
  private OrthographicCamera gamecam;

  public GameRenderer(WorldInformation worldInformation, OrthographicCamera gamecam) {
    this.worldInformation = worldInformation;
    this.gamecam = gamecam;
    orthogRenderer =
        new OrthogonalTiledMapRenderer(
            worldInformation.getTiledMap(),
            SpecialMarioConfiguration.getPixelToMeterConversionRate());
    b2ddr = new Box2DDebugRenderer();
  }

  public void render() {
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
}
