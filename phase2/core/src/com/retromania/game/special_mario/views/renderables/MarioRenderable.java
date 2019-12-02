package com.retromania.game.special_mario.views.renderables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.RetroMania;
import com.retromania.game.shared_abstractions.Renderable;
import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.special_mario.presenter.MarioGamePresentable;

abstract class MarioRenderable implements Renderable, MarioShowable {

  MarioGamePresentable marioGamePresentable;

  private TiledMap currTiledMap;

  private OrthogonalTiledMapRenderer orthogRenderer;
  RetroManiaGame game = RetroMania.getRetroManiaInstance();
  OrthographicCamera gameCam;

  Viewport gamePort;

  MarioRenderable(
      MarioGamePresentable marioGamePresentable,
      OrthogonalTiledMapRenderer orthogRendererr,
      OrthographicCamera gameCam,
      Viewport gamePort) {
    this.marioGamePresentable = marioGamePresentable;
    this.currTiledMap = this.marioGamePresentable.getTileMap();
    this.gamePort = gamePort;
    this.gameCam = gameCam;
    this.orthogRenderer = orthogRendererr;
    setUpOrthogRenderer();
  }

  private void setUpOrthogRenderer() {
    orthogRenderer.setMap(currTiledMap);
  }

  @Override
  public boolean checkTiledMap() {
    if (currTiledMap != this.marioGamePresentable.getTileMap()) {
      currTiledMap = this.marioGamePresentable.getTileMap();
      setUpOrthogRenderer();
      return true;
    }
    return false;
  }

  void clearDisplay() {
    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
  }

  public Viewport getGamePort() {
    return gamePort;
  }

  public void resize(int width, int height) {
    gamePort.update(width, height);
  }

  void displayWorld() {
    orthogRenderer.setView(gameCam);
    orthogRenderer.render();
    game.sb.setProjectionMatrix(gameCam.combined);
  }

  @Override
  public void dispose() {
    orthogRenderer.dispose();
  }

  @Override
  public void setPresenter(MarioGamePresentable marioGamePresentable) {
    this.marioGamePresentable = marioGamePresentable;
  }

  @Override
  public void reloadLevel() {
    marioGamePresentable.reloadLevel();
  }
}
