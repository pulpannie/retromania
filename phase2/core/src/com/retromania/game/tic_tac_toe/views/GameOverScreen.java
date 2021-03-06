package com.retromania.game.tic_tac_toe.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.retromania.game.shared_abstractions.RetroManiaScreen;

/**
 * implements the view when the game is over.
 *
 * @author Hyokyung Kim
 */
public class GameOverScreen extends RetroManiaScreen {
  public SpriteBatch batch;
  private float gameWidth, gameHeight;
  public Stage stage;
  private BitmapFont font = new BitmapFont();
  private String winner;
  private Texture gameOver;

  /** @param winner the winner of the game passed from PlayPresenter. */
  public GameOverScreen(String winner) {
    this.winner = winner;
  }

  /** shows the screen. Creates instances of classes responsible for the interface. */
  @Override
  public void show() {
    OrthographicCamera gameCam;
    gameCam = new OrthographicCamera();
    gameCam.setToOrtho(false, gameWidth, gameHeight);
    gameWidth = Gdx.graphics.getWidth();
    gameHeight = Gdx.graphics.getHeight();

    stage = new Stage(new FitViewport(gameWidth, gameHeight, gameCam));
    Gdx.input.setInputProcessor(stage);
    batch = new SpriteBatch();
    gameOver = new Texture(Gdx.files.internal("tic_tac_toe/game_over.png"));
  }

  /**
   * Renders the screen.
   *
   * @param delta time between the last frame and this frame. displays all variables needed to be
   *     displayed.
   */
  @Override
  public void render(float delta) {
    Gdx.gl.glClearColor(1, 1, 1, 0);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    stage.act(delta);
    stage.draw();
    drawGameOver();
    writeWinner();
  }

  /** draws the gameOver texture on screen. */
  private void drawGameOver() {

    batch.begin();
    batch.draw(gameOver, gameWidth / 4 - 40, gameHeight / 3 + 100, 600, 600);
    batch.end();
  }

  /** writes the winner of the game on screen. */
  private void writeWinner() {
    batch.begin();
    font.setColor(Color.BLACK);
    font.getData().setScale(7, 7);
    font.draw(batch, this.winner + "wins!", gameWidth / 4 + 10, gameHeight / 3);
    batch.end();
  }

  @Override
  public void handleInput() {}

  @Override
  public void resize(int width, int height) {}


  /** disposes a variable after its usage. */
  @Override
  public void dispose() {
    stage.dispose();
  }
}
