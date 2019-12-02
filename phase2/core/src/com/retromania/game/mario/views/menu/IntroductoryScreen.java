package com.retromania.game.mario.views.menu;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.mario.models.utils.LevelPreference;
import com.retromania.game.mario.utils.MainPageUtilsTable;
import com.retromania.game.mario.views.mission.first.FirstMissionView;
import com.retromania.game.mario.views.mission.seccond.SecondMissionView;
import com.retromania.game.mario.views.renderables.UserRenderPreference;

import javax.inject.Inject;
import javax.inject.Singleton;


/**
 *
 * The first menu screen that the user sees
 *
 * */
@Singleton
public class IntroductoryScreen extends MenuOptionScreen {

  private Stage stage;
  private Table table;
  private Skin skin;
  private Viewport viewport2 =
      new FitViewport(RetroManiaGame.V_WIDTH, RetroManiaGame.V_HEIGHT, new OrthographicCamera());
  private UserRenderPreference userRenderPreference;
  private LevelPreference levelPreference;
  private FirstMissionView firstMissionView;
  private SecondMissionView secondMissionView;

  @Inject
  IntroductoryScreen(
      LevelPreference levelPreference,
      FirstMissionView firstMissionView,
      SecondMissionView secondMissionView,
      UserRenderPreference userRenderPreference) {
    this.userRenderPreference = userRenderPreference;
    this.levelPreference = levelPreference;
    this.firstMissionView = firstMissionView;
    this.secondMissionView = secondMissionView;
  }

  @Override
  public void handleInput() {}

  @Override
  public void show() {
    setUpStage();
    setUpSkin();
    setUpTable();
    setUpBrand();
    setUpSettingOptions();
    stage.addActor(table);
    Gdx.input.setInputProcessor(stage);
  }

  private void setUpSettingOptions() {
    table.padTop(30);
    table
        .add(
            new MainPageUtilsTable(
                    "Play Game",
                    "Settings",
                    menuScreen,
                    this,
                    levelPreference,
                    userRenderPreference,
                    game,
                    firstMissionView,
                    secondMissionView)
                .getTable())
        .expandX()
        .expandY()
        .row();
  }

  private void setUpBrand() {
    Label brand = new Label("Our Special Mario", skin);
    brand.setFontScaleY(.4f);
    brand.setFontScaleX(.4f);
    brand.setColor(Color.RED);
    table.add(brand).expandX().row();
  }

  private void setUpTable() {
    table = new Table();
    table.setFillParent(true);
    table.pack();
    table.top();
    table.setFillParent(true);
    table.padTop(10);
  }

  private void setUpSkin() {
    skin = new Skin(Gdx.files.internal("special_mario/ScaleFont.json"));
  }

  private void setUpStage() {
    stage = new Stage(viewport2, game.sb);
  }

  @Override
  public void render(float delta) {
    stage.draw();
  }

  @Override
  public void resize(int width, int height) {
    viewport2.update(width, height);
  }

  @Override
  public void dispose() {
    stage.dispose();
  }
}
