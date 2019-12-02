package com.retromania.game.mario.views.renderables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.mario.presenter.SpecialMarioStarterPresenter;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * A Concrete implementation of MarioRenderable, this renderable will not show the main player, and
 * make the said player, a ghost(invisible), but at the same it will give it its information back to
 * the presenter and from there to the main player, so you will play but you won't see your player.
 */
@Singleton
class GhostRenderer extends MarioRenderable {

  @Inject
  GhostRenderer(
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
    gameCam.position.x =
        gamePort.getWorldWidth() / 2 > marioGamePresentable.getXMainPlayer()
            ? gamePort.getWorldWidth() / 2
            : marioGamePresentable.getXMainPlayer();
  }

  private void handleInput() {
//    if (Gdx.input.isTouched()) {
//      if (Gdx.input.getX() > getGamePort().getScreenWidth() / 2) gameCam.position.x += .1;
//      else gameCam.position.x -= .1;
//    }

    gameCam.position.x =
            gamePort.getWorldWidth() / 2 > marioGamePresentable.getXMainPlayer()
                    ? gamePort.getWorldWidth() / 2
                    : marioGamePresentable.getXMainPlayer();
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
  }

}
