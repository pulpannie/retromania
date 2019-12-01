package com.retromania.game.special_mario.views.renderables;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.special_mario.models.player.MainPlayer;
import com.retromania.game.special_mario.presenter.SpecialMarioStarterPresenter;

import javax.inject.Inject;
import javax.inject.Named;

public class SurvivalRenderer extends MarioRenderable {

  @Inject
  SurvivalRenderer(
      MainPlayer mainPlayer,
      World world,
      SpecialMarioStarterPresenter marioGamePresenter,
      OrthogonalTiledMapRenderer orthogRendererr,
      @Named("Super Mario Game Cam")OrthographicCamera gameCam,
      @Named("Super Mario Game Port") Viewport gamePort) {
    super(mainPlayer, world, marioGamePresenter, orthogRendererr, gameCam, gamePort);
  }

    @Override
    public void start() {
        mainPlayer.createMainPlayer();
        gameCam.position.x =
                gamePort.getWorldWidth() / 2 > mainPlayer.getX() ? gamePort.getWorldWidth() / 2 : mainPlayer.getX();
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
        displayDebugLines();
        displayMainPlayer();
    }

    private void displayDebugLines() {
        b2ddr.render(world, gameCam.combined);
    }

    private void displayMainPlayer() {
        game.sb.begin();
        mainPlayer.draw(game.sb);
        game.sb.end();
    }
}
