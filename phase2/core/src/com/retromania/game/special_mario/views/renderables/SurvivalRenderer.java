package com.retromania.game.special_mario.views.renderables;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.special_mario.models.player.MainPlayer;
import com.retromania.game.special_mario.presenter.SpecialMarioStarterPresenter;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * A Concrete implementation of MarioRenderable, this renderable is will not care about your player,
 * and will leave it by if you let it.
 */
class SurvivalRenderer extends MarioRenderable {

  @Inject
  SurvivalRenderer(
      SpecialMarioStarterPresenter marioGamePresenter,
      OrthogonalTiledMapRenderer orthogRendererr,
      @Named("Super Mario Game Cam") OrthographicCamera gameCam,
      @Named("Super Mario Game Port") Viewport gamePort) {
    super(marioGamePresenter, orthogRendererr, gameCam, gamePort);
  }

  @Override
  public void start() {
    reloadLevel();
    marioGamePresentable.createPlayerFromScratch();
    gameCam.position.x =
        gamePort.getWorldWidth() / 2 > marioGamePresentable.getXMainPlayer()
            ? gamePort.getWorldWidth() / 2
            : marioGamePresentable.getXMainPlayer();
  }

  private void handleInput() {
    gameCam.position.x += .005f;
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
