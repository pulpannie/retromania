package com.retromania.game.tic_tac_toe.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.RetroMania;
import com.retromania.game.shared_abstractions.RetroManiaScreen;
import com.retromania.game.tic_tac_toe.presenters.MenuPresenter;
import com.retromania.game.tic_tac_toe.utils.MenuScreenButtonHelper;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * implements the view during the menu screen.
 *
 * @author Hyokyung Kim.
 */
@Singleton
public class MenuScreen extends RetroManiaScreen {
  private Stage stage;
  private SpriteBatch batch;
  private OrthographicCamera gameCam;
  private BitmapFont font = new BitmapFont();
  private float gameWidth;
  private float gameHeight;
  private ImageButton playButton;
  private Viewport viewport;
  private PlayScreen playScreen;
  private MenuPresenter menuPresenter;
  private Texture title;
  private MenuScreenButtonHelper menuScreenButtonHelper;

  /**
   * @param gameCam new OrthographicCamera instantiated through dagger.
   * @param playScreen new PlayScreen instantiated through dagger. creates a new instance of
   *     menuPresenter, menuScreenButtonHelper.
   */
  @Inject
  public MenuScreen(OrthographicCamera gameCam, PlayScreen playScreen) {
    this.menuPresenter = new MenuPresenter();
    this.playScreen = playScreen;
    this.gameCam = gameCam;
    this.menuScreenButtonHelper = new MenuScreenButtonHelper(menuPresenter);
  }

  /** shows the screen. instantiates all variables needed for the interface. */
  @Override
  public void show() {
    ArrayList<ImageButton> imageButtons;

    gameWidth = Gdx.graphics.getWidth();
    gameHeight = Gdx.graphics.getHeight();
    viewport = new FillViewport(gameWidth, gameHeight, gameCam);
    batch = new SpriteBatch();
    title = new Texture(Gdx.files.internal("tic_tac_toe/title.png"));
    stage = new Stage(viewport, game.sb);

    imageButtons = menuScreenButtonHelper.makeButtons();
    for (int i = 0; i < imageButtons.size(); i++) {
      stage.addActor(imageButtons.get(i));
    }

    Gdx.input.setInputProcessor(stage);

    playButton = menuScreenButtonHelper.getPlayButton();
  }

  /** handles user input. */
  public void handleInput() {
    if (playButton.isPressed()) {
      RetroMania.getRetroManiaInstance().setScreen(playScreen);
    }
  }

  /**
   * Renders the screen.
   *
   * @param delta time between the last frame and this frame. displays all variables needed to be
   *     displayed.
   */
  @Override
  public void render(float delta) {
    handleInput();
    Gdx.gl.glClearColor(1, 1, 1, 0);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    stage.act(delta);
    game.sb.setProjectionMatrix(stage.getCamera().combined);
    stage.draw();
    batch.setProjectionMatrix(gameCam.combined);
    drawTitle();
    writeCats();
    writeSize();
  }

  /** draws the Title of the game on the screen. */
  private void drawTitle() {
    batch.begin();
    batch.draw(title, gameWidth * 2 / 6 + 40, gameHeight * 2 / 3, 500, 150);
    batch.end();
  }

  /** writes the label for the catButton on the screen. */
  private void writeCats() {
    batch.begin();
    font.setColor(Color.BLACK);
    font.getData().setScale(2, 2);
    font.draw(batch, "CATS!", gameWidth / 2 - 120, gameHeight / 4 - 70);
    batch.end();
  }

  /** writes the size of the game on the screen. */
  private void writeSize() {
    batch.begin();
    font.draw(
        batch,
        Integer.toString(menuPresenter.getGameSize()),
        gameWidth / 2 + 65,
        gameHeight / 4 - 20);
    batch.end();
  }

  /**
   * @param width resize the screen to this width
   * @param height resize the screen to this height
   */
  @Override
  public void resize(int width, int height) {
    viewport.update(width, height);
  }

  /** disposes a variable after its usage. */
  public void dispose() {
    stage.dispose();
  }
}
