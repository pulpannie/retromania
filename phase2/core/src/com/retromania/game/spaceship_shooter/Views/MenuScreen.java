package com.retromania.game.spaceship_shooter.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.RetroMania;
import com.retromania.game.shared_abstractions.RetroManiaScreen;
import com.retromania.game.spaceship_shooter.Models.ImageButtonBuilder;
import com.retromania.game.spaceship_shooter.Presenters.MenuScreenPresenter;

/**
 * View class of the menu screen. It is responsible for rendering the menu screen, and has access to
 * all the variables that are required for the render, being rendered and/or interact with the
 * users.
 *
 * @author Thuy, Umid
 */
public class MenuScreen extends RetroManiaScreen {

  /** Stage that holds the startButton to print */
  public Stage stage;

  /** The game port instance of the screen. */
  private Viewport gamePort;

  /** The game camera instance of the screen. */
  private OrthographicCamera gameCam;

  /** The start button that are rendered and take the user to the play screen if pressed. */
  private ImageButton startButton;

  /** The screen presenter. */
  private MenuScreenPresenter presenter;

  /**
   * Constructor for the screen view class.
   *
   * @param mainScreen access to viewport of starter class through interface
   */
  public MenuScreen(MainScreenInterface mainScreen) {
    presenter = new MenuScreenPresenter(mainScreen);

    gameCam = new OrthographicCamera();
    gamePort = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), gameCam);

    stage = new Stage(gamePort, RetroMania.getRetroManiaInstance().sb);
    startButton = (new ImageButtonBuilder()).buildTexture("start_button.png").buildButton();
    startButton.setPosition(Gdx.graphics.getWidth() / 2 - 400, Gdx.graphics.getHeight() / 2 - 600);
    startButton.setSize(800, 800);
    stage.addActor(startButton);
    Gdx.input.setInputProcessor(stage);
  }

  /** Show the screen. */
  @Override
  public void show() {
    stage.addActor(startButton);
    presenter.updateTable();

    Gdx.input.setInputProcessor(stage);
  }

  /** Handle the user input. */
  public void handleInput() {
    if (startButton.isPressed()) start();
  }

  /** Update the screen. */
  public void update(float dt) {
    gameCam.update();
    handleInput();

    stage.act();
    presenter.update(dt);
  }

  /** Render the screen */
  @Override
  public void render(final float delta) {
    update(delta);

    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    RetroMania.getRetroManiaInstance().sb.begin();
    presenter.getBackground().draw(RetroMania.getRetroManiaInstance().sb, delta);
    RetroMania.getRetroManiaInstance().sb.end();
    stage.draw();
    presenter.getStage().draw();
  }

  /**
   * Resize the screen with the value of width and height.
   *
   * @param width the width of the screen.
   * @param height the height of the screen.
   */
  @Override
  public void resize(int width, int height) {
    gamePort.update(width, height);
  }

  @Override
  public void pause() {}

  @Override
  public void resume() {}

  /** Start the game. */
  private void start() {
    dispose();
    presenter.start();
  }

  @Override
  public void hide() {}

  /** Dispose the stage. */
  @Override
  public void dispose() {
    stage.dispose();
  }
}
