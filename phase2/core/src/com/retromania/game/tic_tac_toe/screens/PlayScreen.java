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
import com.retromania.game.tic_tac_toe.utils.UserPrefrence;

import javax.inject.Inject;

public class PlayScreen extends RetroManiaScreen {
  public Stage stage;
  public static ShapeRenderer boardRenderer = new ShapeRenderer();
  public SpriteBatch batch;
  public Texture cross;
  public Texture circle;
  public OrthographicCamera gamecam;
  BitmapFont font = new BitmapFont();
  TicTacToe ticTacToe;
  public float gameWidth, gameHeight;
  CellManager cellManager;
  String winner;
  //    GameSaver gameSaver;
  User currentUser;

  UserPrefrence userPrefrence;

  @Inject
  public PlayScreen(UserPrefrence userPrefrence, CellManager cellManager) {
    this.userPrefrence = userPrefrence;
    this.cellManager = cellManager;
  }

  @Override
  public void show() {

    if (userPrefrence.getCat()) {
      cross = new Texture(Gdx.files.internal("tic_tac_toe/cat2.png"));
      circle = new Texture(Gdx.files.internal("tic_tac_toe/cat3.png"));
    } else {
      cross = new Texture(Gdx.files.internal("tic_tac_toe/cross.jpg"));
      circle = new Texture(Gdx.files.internal("tic_tac_toe/circle.png"));
    }
    gameWidth = Gdx.graphics.getWidth();
    gameHeight = Gdx.graphics.getHeight();
    ticTacToe = new TicTacToe(userPrefrence, cellManager);

    gamecam = new OrthographicCamera();
    gamecam.setToOrtho(false, gameWidth, gameHeight);
    stage = new Stage(new FitViewport(gameWidth, gameHeight, gamecam));
    Gdx.input.setInputProcessor(stage);
    batch = new SpriteBatch();
    batch.setProjectionMatrix(gamecam.combined);
    cellManager.show();
  }

  @Override
  public void render(float delta) {
    Cell[][] cellArray = ticTacToe.getCellStates();
    Gdx.gl.glClearColor(1, 1, 1, 0);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    stage.act(delta);
    stage.draw();
    for (int i = 1; i < userPrefrence.getGameSize(); i++) {
      DrawBoardLine(
          new Vector2(gameWidth * i / userPrefrence.getGameSize(), 0),
          new Vector2(gameWidth * i / userPrefrence.getGameSize(), gameHeight),
          gamecam.combined);
      DrawBoardLine(
          new Vector2(0, gameHeight * i / userPrefrence.getGameSize()),
          new Vector2(gameWidth, gameHeight * i / userPrefrence.getGameSize()),
          gamecam.combined);
    }

    if (Gdx.input.isTouched()) {
      Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
      System.out.println(mousePos);
      Vector3 worldCoordinates = gamecam.unproject(mousePos);

      int tmpx = (int)((worldCoordinates.x * userPrefrence.getGameSize())/gameWidth);
      int tmpy = (int)((worldCoordinates.y * userPrefrence.getGameSize())/gameHeight);
      ticTacToe.touchCell(tmpx, tmpy);
    }
    for (int i = 0; i < userPrefrence.getGameSize(); i++) {
      for (int j = 0; j < userPrefrence.getGameSize(); j++) {
        if (!cellArray[i][j].getCell().equals("None")) {
          batch.begin();
          batch.draw(
              this.getCell(cellArray[i][j].getCell()),
              gameWidth * i / userPrefrence.getGameSize(),
              gameHeight * j / userPrefrence.getGameSize(),
              gameWidth / userPrefrence.getGameSize(),
              gameHeight / userPrefrence.getGameSize());
          batch.end();
        }
      }
    }
    if (ticTacToe.isEnd()) {
      winner = ticTacToe.getWinner();
      if (winner.equals("None")) {
        game.setScreen(new GameOverScreen(game, "No one"));
      } else if (winner.equals("Cross")) {
        //                currentUser = gameSaver.getCurrentUser();
        //                gameSaver.setScore(currentUser.getScore() + 1);
        game.setScreen(new GameOverScreen(game, "Cross"));

      } else if (winner.equals("Circle")) {
        game.setScreen(new GameOverScreen(game, "Circle"));
      }
    }
  }

  public static void DrawBoardLine(Vector2 start, Vector2 end, Matrix4 projectionMatrix) {
    Gdx.gl.glLineWidth(15);
    boardRenderer.setProjectionMatrix(projectionMatrix);
    boardRenderer.begin(ShapeRenderer.ShapeType.Line);
    boardRenderer.setColor(Color.BLACK);
    boardRenderer.line(start, end);
    boardRenderer.end();
  }

  public Texture getCell(String string) {
    if (string == "Cross") {
      return cross;
    } else if (string == "Circle") {
      return circle;
    }
    return null;
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
