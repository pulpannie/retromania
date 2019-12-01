package com.retromania.game.spaceship_shooter.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.RetroMania;
import com.retromania.game.shared_abstractions.RetroManiaScreen;
import com.retromania.game.spaceship_shooter.models.ImageButtonBuilder;
import com.retromania.game.spaceship_shooter.presenters.PlayScreenPresenter;

/**
 * View class of the play screen. It is responsible for rendering the play screen, and has access to
 * all the variables that are required for the render, being rendered and/or interact with the
 * users.
 *
 * @author Thuy, Umid
 */
public class PlayScreen extends RetroManiaScreen {

  /** Stage that holds the startButton to print */
  private Stage stage;

  /** The game port instance of the screen. */
  private Viewport gamePort;

  /** The game camera instance of the screen. */
  private OrthographicCamera gameCam;

  /** The left button that are rendered and move the car to the left if pressed. */
  private ImageButton leftButton;

  /** The right button that are rendered and move the car to the right if pressed. */
  private ImageButton rightButton;

  /**
   * The pause button that are rendered, it will pause the screen and move the user to the pause
   * screen if pressed.
   */
  private ImageButton pauseButton;

  /** The screen presenter. */
  private PlayScreenPresenter presenter;

  /**
   * Constructor for the screen view class.
   *
   * @param mainScreen access to viewport of starter class through interface
   */
  public PlayScreen(MainScreenInterface mainScreen) {
    presenter = new PlayScreenPresenter(4, mainScreen);

    gameCam = new OrthographicCamera();
    gamePort = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), gameCam);

    stage = new Stage(gamePort, RetroMania.getRetroManiaInstance().sb);

    leftButton = (new ImageButtonBuilder()).buildTexture("left_button_big.png").buildButton();
    leftButton.setPosition(0, 0);
    leftButton.setSize(250, 250);
    stage.addActor(leftButton);

    rightButton = (new ImageButtonBuilder()).buildTexture("right_button_big.png").buildButton();
    rightButton.setPosition(Gdx.graphics.getWidth() - 250, 0);
    rightButton.setSize(250, 250);
    stage.addActor(rightButton);

    pauseButton = (new ImageButtonBuilder()).buildTexture("pause.png").buildButton();
    pauseButton.setPosition(Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 350);
    pauseButton.setSize(180, 180);
    stage.addActor(pauseButton);
    Gdx.input.setInputProcessor(stage);
  }

  /** Show the screen. */
  @Override
  public void show() {
    stage.addActor(pauseButton);
    stage.addActor(rightButton);
    stage.addActor(leftButton);
    Gdx.input.setInputProcessor(stage);
  }

  /** Handle the user input. */
  public void handleInput() {
    if (rightButton.isPressed()) {
      presenter.moveCarRight();
    } else if (leftButton.isPressed()) {
      presenter.moveCarLeft();
    } else if (pauseButton.isPressed()) {
      pause();
    } else if (Gdx.input.isTouched()) presenter.shoot();
  }

  /** Update the screen. */
  public void update(float dt) {
    gameCam.update();
    handleInput();
    stage.act();
    presenter.update(dt);
    if (presenter.isFinished()) endGame();
  }

  /** Render the screen */
  @Override
  public void render(final float delta) {
    update(delta);

    Gdx.gl.glClearColor(0, 0, 0, 1);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

    RetroMania.getRetroManiaInstance().sb.begin();
    presenter.getBackground().draw(RetroMania.getRetroManiaInstance().sb, delta);
    for (Actor actor : presenter.getRenderableActors())
      actor.draw(RetroMania.getRetroManiaInstance().sb, delta);
    RetroMania.getRetroManiaInstance().sb.end();
    stage.draw();
    presenter.getHudStage().draw();
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

  /** Start the game. */
  @Override
  public void pause() {
    dispose();
    presenter.pause();
  }

  @Override
  public void resume() {}

  /** End the game and return to the menu screen. */
  private void endGame() {
    dispose();
    presenter.endGame();
  }

  @Override
  public void hide() {}

  /** Dispose the stage. */
  @Override
  public void dispose() {
    stage.dispose();
  }
}
