package com.retromania.game.special_mario.views.renderables;

import com.badlogic.gdx.physics.box2d.World;
import com.retromania.game.special_mario.models.player.MainPlayer;
import com.retromania.game.special_mario.presenter.SpecialMarioStarterPresenter;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class NormalRenderer extends MarioRenderable {



  @Inject
  NormalRenderer(
          MainPlayer mainPlayer,
          World world, SpecialMarioStarterPresenter marioGamePresenter) {
    super(mainPlayer, world, marioGamePresenter);
  }



  private void handleInput() {
    gamecam.position.x =
        gamePort.getWorldWidth() / 2 > mainPlayer.getX() ? gamecam.position.x : mainPlayer.getX();
  }

  public void update() {
    handleInput();
    gamecam.update();
    checkTiledMap();
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
    orthogRenderer.setView(gamecam);
    orthogRenderer.render();
    game.sb.setProjectionMatrix(gamecam.combined);
  }

  private void displayMainPlayer() {
    game.sb.begin();
    mainPlayer.draw(game.sb);
    game.sb.end();
  }


}
