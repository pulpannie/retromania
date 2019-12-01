package com.retromania.game.tic_tac_toe.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.retromania.game.shared_abstractions.RetroManiaScreen;
import com.retromania.game.shared_abstractions.User;
import com.retromania.game.tic_tac_toe.individuals.Cell;
import com.retromania.game.tic_tac_toe.individuals.CellManager;
import com.retromania.game.tic_tac_toe.individuals.TicTacToe;
import com.retromania.game.tic_tac_toe.presenters.PlayPresenter;
import com.retromania.game.tic_tac_toe.utils.UserPrefrence;
import com.retromania.game.utils.GameSaver;

import javax.inject.Inject;

public class PlayScreen extends RetroManiaScreen {
  public Stage stage;
  public static ShapeRenderer boardRenderer = new ShapeRenderer();
  public SpriteBatch batch;
  public OrthographicCamera gamecam;
  BitmapFont font = new BitmapFont();
  public float gameWidth, gameHeight;
  CellManager cellManager;
  PlayPresenter playPresenter;

  @Inject
  public PlayScreen() {
    this.playPresenter = new PlayPresenter();
  }

  @Override
  public void show() {
    playPresenter.setTextures();
    gameWidth = Gdx.graphics.getWidth();
    gameHeight = Gdx.graphics.getHeight();
    playPresenter.createTicTacToe();

    gamecam = new OrthographicCamera();
    gamecam.setToOrtho(false, gameWidth, gameHeight);
    stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), gamecam));
    Gdx.input.setInputProcessor(stage);
    batch = new SpriteBatch();
    batch.setProjectionMatrix(gamecam.combined);
  }

  @Override
  public void render(float delta) {
    Cell[][] cellArray = playPresenter.getCells();
    Gdx.gl.glClearColor(1, 1, 1, 0);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    game.sb.setProjectionMatrix(stage.getCamera().combined);
    stage.act(delta);
    stage.draw();
    for (int i = 1; i < playPresenter.getSize(); i++) {
      DrawBoardLine(
          new Vector2(gameWidth * i / playPresenter.getSize(), 0),
          new Vector2(gameWidth * i / playPresenter.getSize(), gameHeight),
          gamecam.combined);
      DrawBoardLine(
          new Vector2(0, gameHeight * i / playPresenter.getSize()),
          new Vector2(gameWidth, gameHeight * i / playPresenter.getSize()),
          gamecam.combined);
    }

    if (Gdx.input.isTouched()) {
      Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
      Vector3 worldCoordinates = gamecam.unproject(mousePos);
      playPresenter.touchCells(worldCoordinates.x, worldCoordinates.y);
    }
    for (int i = 0; i < playPresenter.getSize(); i++) {
      for (int j = 0; j < playPresenter.getSize(); j++) {
        if (!cellArray[i][j].getCell().equals("None")) {
          batch.begin();
          batch.draw(
              playPresenter.convertCell(cellArray[i][j].getCell()),
              gameWidth * i / playPresenter.getSize(),
              gameHeight * j / playPresenter.getSize(),
              gameWidth / playPresenter.getSize(),
              gameHeight / playPresenter.getSize());
          batch.end();
        }
      }
    }
    playPresenter.checkEnd();
  }

  public static void DrawBoardLine(Vector2 start, Vector2 end, Matrix4 projectionMatrix) {
    Gdx.gl.glLineWidth(15);
    boardRenderer.setProjectionMatrix(projectionMatrix);
    boardRenderer.begin(ShapeRenderer.ShapeType.Line);
    boardRenderer.setColor(Color.BLACK);
    boardRenderer.line(start, end);
    boardRenderer.end();
  }


  @Override
  public void resize(int width, int height) {}

  @Override
  public void handleInput() {}

  @Override
  public void pause() {}

  @Override
  public void resume() {}

  @Override
  public void hide() {}

  @Override
  public void dispose() {}
}
