package com.retromania.game.special_mario.views.renderables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.special_mario.models.player.MainPlayer;
import com.retromania.game.special_mario.presenter.SpecialMarioStarterPresenter;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;


@Singleton
public class GhostRenderer extends MarioRenderable {

  @Inject
  GhostRenderer(
      MainPlayer mainPlayer,
      World world,
      SpecialMarioStarterPresenter marioGamePresenter,
      OrthogonalTiledMapRenderer orthogonalTiledMapRenderer,
      @Named("Super Mario Game Cam") OrthographicCamera gameCam,
      @Named("Super Mario Game Port") Viewport gamePort) {
    super(mainPlayer, world, marioGamePresenter, orthogonalTiledMapRenderer, gameCam, gamePort);
  }


  @Override
  public void start() {
    mainPlayer.createMainPlayer();
    gameCam.position.x =
            gamePort.getWorldWidth() / 2 > mainPlayer.getX() ? gamePort.getWorldWidth() / 2 : mainPlayer.getX();
  }

  private void handleInput() {
    if (Gdx.input.isTouched()) {
      if (Gdx.input.getX() > getGamePort().getScreenWidth() / 2) gameCam.position.x += .1;
      else gameCam.position.x -= .1;
    }
  }

  void update() {
    handleInput();
    gameCam.update();
    checkTiledMap();
  }

  @Override
  public void render(float delta) {
    update();
    clearDisplay();
    displayWorld();
    displayDebugLines();
  }

  private void displayDebugLines() {
    b2ddr.render(world, gameCam.combined);
  }
}
