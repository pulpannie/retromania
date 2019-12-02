package com.retromania.game.mario.views.renderables;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.mario.presenter.SpecialMarioStarterPresenter;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * A Concrete implementation of MarioRenderable, this renderable is will not care about your player,
 * and will leave it by if you let it.
 */
@Singleton
class SurvivalRenderer extends MarioRenderable {
  private boolean canUpdateCam = false;

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
    canUpdateCam = false;
    reloadLevel();
    marioGamePresentable.createPlayerFromScratch();
    gameCam.position.x =
        gamePort.getWorldWidth() / 2 > marioGamePresentable.getXMainPlayer()
            ? gamePort.getWorldWidth() / 2
            : marioGamePresentable.getXMainPlayer();
    Timer.schedule(
        new Timer.Task() {
          @Override
          public void run() {
            canUpdateCam = true;
          }
        },
        1);
    gameCam.update();
  }

  private void handleInput() {
    if (canUpdateCam) gameCam.position.x += .01f;
  }

  public void update() {
    gameCam.update();
    handleInput();
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
