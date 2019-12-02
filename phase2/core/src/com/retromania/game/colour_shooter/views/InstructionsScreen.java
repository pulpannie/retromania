package com.retromania.game.colour_shooter.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.colour_shooter.ColourShooterStarter;
import com.retromania.game.colour_shooter.models.Background;
import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

/**
 * This is the instructions screen. This is the first screen displayed when game starts.
 */
public class InstructionsScreen implements Screen {

  private RetroManiaGame game;
  private static Stage stage;
  private Viewport gamePort;
  ColourShooterStarter mainscreen;
  private int width;
  private int height;

  /**
   * @param game:       a RetroManiaGame
   * @param mainscreen: mainscreen instance of ColourShooterStarter
   */
  public InstructionsScreen(RetroManiaGame game, ColourShooterStarter mainscreen) {
    this.game = game;
    this.mainscreen = mainscreen;
    width = 400;
    height = 800;
    stage = new Stage(new StretchViewport(width, height, new OrthographicCamera()));
  }

  /**
   * Displaying the background. Various images and the back button to go back to the MenuScreen so
   * then you can play the game.
   */
  @Override
  public void show() {
    Background background = new Background("instructions_screen");
    Image background_img = new Image(background.getBackgroundTexture());
    background_img.setSize(width, height);

    stage.addActor(background_img);

    Image instructionsImg =
            new Image(new Texture(Gdx.files.internal("colour_shooter/instructions.png")));
    instructionsImg.setSize((float) (width * 0.85), (float) (height * 0.75));
    instructionsImg.setPosition(
            (float) (width / 2.2) - (instructionsImg.getWidth() / 2),
            (float) (height / 2.2) - (instructionsImg.getHeight() / 2));
    stage.addActor(instructionsImg);

    Image backImage = new Image(new Texture(Gdx.files.internal("colour_shooter/back_wth_txt.png")));
    backImage.setSize((float) (width * 0.4), (float) (height * 0.1));
    backImage.setPosition(width / 2 - (backImage.getWidth() / 2), (float) (height * 0.87));

    stage.addActor(backImage);

    backImage.addListener(
            new ClickListener() {
              @Override
              public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(ColourShooterStarter.getMenuScreen());
              }
            });

    Gdx.input.setInputProcessor(stage);
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    stage.draw();
  }

  @Override
  public void resize(int w, int h) {
    stage.getViewport().update(w, h, false);
  }

  @Override
  public void pause() {
  }

  @Override
  public void resume() {
  }

  @Override
  public void hide() {
  }

  @Override
  public void dispose() {
    stage.dispose();
  }
}
