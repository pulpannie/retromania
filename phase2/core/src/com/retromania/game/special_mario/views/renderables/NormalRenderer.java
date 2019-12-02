package com.retromania.game.special_mario.views.renderables;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.special_mario.presenter.SpecialMarioStarterPresenter;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * A Concrete implementation of MarioRenderable, this renderable is a normal renderable for showing
 * the game.
 */
@Singleton
class NormalRenderer extends MarioRenderable {

  @Inject
  NormalRenderer(
      SpecialMarioStarterPresenter marioGamePresenter,
      OrthogonalTiledMapRenderer orthogonalTiledMapRenderer,
      @Named("Super Mario Game Cam") OrthographicCamera gameCam,
      @Named("Super Mario Game Port") Viewport gamePort) {
    super(marioGamePresenter, orthogonalTiledMapRenderer, gameCam, gamePort);
  }

  @Override
  public void start() {
    reloadLevel();
    marioGamePresentable.createPlayerFromScratch();
      handleInput();
  }

  private void handleInput() {
    gameCam.position.x =
        gamePort.getWorldWidth() / 2 > marioGamePresentable.getXMainPlayer()
            ? gamePort.getWorldWidth() / 2
            : marioGamePresentable.getXMainPlayer();
  }

  public void update() {
    handleInput();
    gameCam.update();
    checkTiledMap();
  }

  @Override
  public void render(float delta) {
    update();
    clearDisplay();
    displayWorld();
    displayMainPlayer();
  }


  private void displayMainPlayer() {
    game.sb.begin();
    marioGamePresentable.letMainPlayerShow(game.sb);
    game.sb.end();
  }
}
