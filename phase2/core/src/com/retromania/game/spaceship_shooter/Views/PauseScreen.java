package com.retromania.game.spaceship_shooter.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.RetroMania;
import com.retromania.game.shared_abstractions.RetroManiaScreen;
import com.retromania.game.spaceship_shooter.Models.ImageButtonBuilder;
import com.retromania.game.spaceship_shooter.Presenters.PauseScreenPresenter;

/**
 * View class of the pause screen. It is responsible for rendering the pause screen, and has access
 * to all the variables that are required for the render, being rendered and/or interact with the
 * users.
 *
 * @author Thuy, Umid
 */
public class PauseScreen extends RetroManiaScreen {

  /** Stage that holds the startButton to print */
  private Stage stage;

  /** The game port instance of the screen. */
  private Viewport gamePort;

  /** The game camera instance of the screen. */
  private OrthographicCamera gameCam;

  /**
   * The resume button that are rendered and take the user back to the current play screen if
   * pressed.
   */
  private ImageButton resumeButton;

  /**
   * The restart button that are rendered and take the user back to the newly created play screen if
   * pressed.
   */
  private ImageButton restartButton;

  /** The restart button that are rendered and take the user to the setting screen if pressed. */
  private ImageButton settingButton;

  /** The screen presenter. */
  private PauseScreenPresenter presenter;

  /**
   * Constructor for the screen view class.
   *
   * @param mainScreen access to viewport of starter class through interface
   */
  public PauseScreen(MainScreenInterface mainScreen) {
    presenter = new PauseScreenPresenter(mainScreen);

    gameCam = new OrthographicCamera();
    gamePort = new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), gameCam);

    stage = new Stage(gamePort, RetroMania.getRetroManiaInstance().sb);

    resumeButton = (new ImageButtonBuilder()).buildTexture("resume.png").buildButton();
    resumeButton.setPosition(Gdx.graphics.getWidth() / 2 - 150, Gdx.graphics.getHeight() / 2 + 100);
    resumeButton.setSize(300, 300);

    restartButton = (new ImageButtonBuilder()).buildTexture("restart.png").buildButton();
    restartButton.setPosition(Gdx.graphics.getWidth() / 2 - 110, Gdx.graphics.getHeight() / 2 - 50);
    restartButton.setSize(200, 200);

    settingButton = (new ImageButtonBuilder()).buildTexture("setting.png").buildButton();
    settingButton.setPosition(
        Gdx.graphics.getWidth() / 2 - 110, Gdx.graphics.getHeight() / 2 - 300);
    settingButton.setSize(200, 200);

    Gdx.input.setInputProcessor(stage);
  }

  /** Show the screen. */
  @Override
  public void show() {
    stage.addActor(resumeButton);
    stage.addActor(restartButton);
    stage.addActor(settingButton);
    Gdx.input.setInputProcessor(stage);
  }

  /** Handle the user input. */
  public void handleInput() {
    if (resumeButton.isPressed()) {
      resume();
    } else if (restartButton.isPressed()) {
      restart();
    } else if (settingButton.isPressed()) {
      modify();
    }
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

  /** Resume the game. */
  @Override
  public void resume() {
    dispose();
    presenter.resume();
  }

  /** Restart the game. */
  private void restart() {
    dispose();
    presenter.restart();
  }

  /** Go to setting. */
  private void modify() {
    dispose();
    presenter.modify();
  }

  @Override
  public void hide() {}

  /** Dispose the stage. */
  @Override
  public void dispose() {
    stage.dispose();
  }
}
