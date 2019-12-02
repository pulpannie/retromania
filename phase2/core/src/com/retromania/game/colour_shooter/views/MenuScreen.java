package com.retromania.game.colour_shooter.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.retromania.game.colour_shooter.ColourShooterStarter;
import com.retromania.game.colour_shooter.models.Background;
import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.badlogic.gdx.scenes.scene2d.Stage;

/**
 * THE MAIN MENU-SCREEN. This class is responsible for drawing our buttons such as the Play, Rules
 * etc.
 */
public class MenuScreen implements Screen {
  private RetroManiaGame game;
  private static Stage stage;

  public SpriteBatch batch;
  private int width;
  private int height;

  public MenuScreen(RetroManiaGame game, ColourShooterStarter mainscreen) {
    OrthographicCamera camera;
    this.game = game;
    camera = new OrthographicCamera();
    batch = new SpriteBatch();
    stage =
            new Stage(new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), camera));
    width = Gdx.graphics.getWidth();
    height = Gdx.graphics.getHeight();
  }

  /**
   * SHOWS THE VARIOUS BUTTONS. Draw the buttons on the main screen.
   */
  @Override
  public void show() {
    Background background = new Background("menu screen");
    Image back_img = new Image(background.getBackgroundTexture());
    back_img.setSize(width, height);
    stage.addActor(back_img);

    Image title = new Image(new Texture(Gdx.files.internal("colour_shooter/title.png")));
    title.setSize((float) (width * 0.9), (float) (height * 0.16));
    title.setPosition((float) (width / 2) - (title.getWidth() / 2), (float) (height * 0.80));
    stage.addActor(title);

    Image play = new Image(new Texture(Gdx.files.internal("colour_shooter/play_wth_text.png")));
    play.setSize((float) (width * 0.4), (float) (height * 0.18));
    play.setPosition((float) (width / 2) - (title.getWidth() / 2), (float) (height * 0.6));
    play.addListener(
            new ClickListener() {
              @Override
              public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(ColourShooterStarter.getPlayScreen());
              }
            });

    stage.addActor(play);

    Image rules = new Image(new Texture(Gdx.files.internal("colour_shooter/rules_wth_txt.png")));
    rules.setSize((float) (width * 0.5), (float) (height * 0.18));
    rules.setPosition((float) (width / 2) - (title.getWidth() / 2), (float) (height * 0.4));
    rules.addListener(
            new ClickListener() {
              @Override
              public void clicked(InputEvent event, float x, float y) {
                dispose();
                game.setScreen(ColourShooterStarter.getInstructionsScreen());
          }
            });
    stage.addActor(rules);

    Image skins = new Image(new Texture(Gdx.files.internal("colour_shooter/skins_wth_txt.png")));
    skins.setSize((float) (width * 0.5), (float) (height * 0.18));
    skins.setPosition((float) (width / 2) - (title.getWidth() / 2), (float) (height * 0.2));
    stage.addActor(skins);

    Gdx.input.setInputProcessor(stage);
  }

  public void update(float dt) {
    stage.act(dt);
  }

  @Override
  public void render(float delta) {
    Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    update(delta);
    stage.draw();
    batch.begin();
    batch.end();
  }

  @Override
  public void resize(int width, int height) {
    stage.getViewport().update(width, height, false);
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
    batch.dispose();
  }
}
