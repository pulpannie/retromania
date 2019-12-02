package com.retromania.game.tic_tac_toe.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.retromania.game.RetroMania;
import com.retromania.game.shared_abstractions.RetroManiaScreen;
import com.retromania.game.tic_tac_toe.presenters.PlayPresenter;

import javax.inject.Inject;

/**
 * implements the view during the game.
 * @author Hyokyung Kim
 */
public class PlayScreen extends RetroManiaScreen {
  private Stage stage;
  private static ShapeRenderer boardRenderer = new ShapeRenderer();
  private SpriteBatch batch;
  private OrthographicCamera gamecam;
  private float gameWidth, gameHeight;
  private PlayPresenter playPresenter;
  private Texture cross, circle;
  private String winner;

  /**
   * creates an instance of PlayPresenter.
   */
  @Inject
  public PlayScreen() {
    this.playPresenter = new PlayPresenter();
  }

  /**
   * Shows the screen.
   * Creates instances of classes responsible for the interface.
   */
  @Override
  public void show() {
    setTextures();
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

  /**
   * Renders the screen.
   * @param delta time between the last frame and this frame.
   * displays all variables needed to be displayed.
   */
  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(1, 1, 1, 0);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    RetroMania.getRetroManiaInstance().sb.setProjectionMatrix(stage.getCamera().combined);
    stage.act(delta);
    stage.draw();
    drawBoard();
    handleInput();
    drawContents();
    winner = playPresenter.handleEnd();
    if (winner!= null){
      RetroMania.getRetroManiaInstance().setScreen(new GameOverScreen(winner));
    };
  }

  /**
   * draws the contents of the TicTacToe board.
   */
  private void drawContents(){
    for (int i = 0; i < playPresenter.getSize(); i++) {
      for (int j = 0; j < playPresenter.getSize(); j++) {
        if (playPresenter.isCellTouched(i, j)) {
          batch.begin();
          batch.draw(
                  convertCell(playPresenter.getCellState(i, j)),
                  gameWidth * i / playPresenter.getSize(),
                  gameHeight * j / playPresenter.getSize(),
                  gameWidth / playPresenter.getSize(),
                  gameHeight / playPresenter.getSize());
          batch.end();
        }
      }
    }
  }

  /**
   * draws the lines for the TicTacToe board.
   */
  private void drawBoard(){
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
  }

  /**
   * draws lines on the screen.
   * @param start start coordinate of the line.
   * @param end end coordinate of the line.
   * @param projectionMatrix projectionMatrix the boardRenderer variable will use to configure its own matrix.
   */
  public static void DrawBoardLine(Vector2 start, Vector2 end, Matrix4 projectionMatrix) {
    Gdx.gl.glLineWidth(15);
    boardRenderer.setProjectionMatrix(projectionMatrix);
    boardRenderer.begin(ShapeRenderer.ShapeType.Line);
    boardRenderer.setColor(Color.BLACK);
    boardRenderer.line(start, end);
    boardRenderer.end();
  }

  /**
   * handles user input.
   * if a user touches the screen, gets appropriate game coordinates x,y and passes it onto the presenter.
   */
  @Override
  public void handleInput() {
    if (Gdx.input.isTouched()) {
      Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
      Vector3 worldCoordinates = gamecam.unproject(mousePos);
      playPresenter.touchCells(worldCoordinates.x, worldCoordinates.y);
    }
  }

  /**
   * initializes the Texture instances cross and circle based on user preference.
   */
  public void setTextures(){
    if (playPresenter.isCat()) {
      cross = new Texture(Gdx.files.internal("tic_tac_toe/cat2.png"));
      circle = new Texture(Gdx.files.internal("tic_tac_toe/cat3.png"));
    } else {
      cross = new Texture(Gdx.files.internal("tic_tac_toe/cross.jpg"));
      circle = new Texture(Gdx.files.internal("tic_tac_toe/circle.png"));
    }
  }

  /**
   * converts the string information inside Cells to their appropriate texture variables.
   * @param string information of the state of a specific Cell
   * @return the matching Texture instance.
   */
  public Texture convertCell(String string) {
    if (string.equals("Cross")) {
      return cross;
    } else if (string.equals("Circle")) {
      return circle;
    }
    return null;
}


  @Override
  public void resize(int width, int height) {}

  @Override
  public void pause() {}

  @Override
  public void resume() {}

  @Override
  public void hide() {}

  /**
   * disposes a variable after its usage.
   */
  @Override
  public void dispose() {
    stage.dispose();
  }
}
