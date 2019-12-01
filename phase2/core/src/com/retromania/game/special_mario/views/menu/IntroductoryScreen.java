package com.retromania.game.special_mario.views.menu;

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
import com.retromania.game.special_mario.utils.MainPageUtilsTable;
import com.retromania.game.special_mario.views.renderables.UserRenderPreference;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class IntroductoryScreen extends MenuOptionScreen {

  private Stage stage;
  private Table table;
  Skin skin;
  Viewport viewport2 =
      new FitViewport(RetroManiaGame.V_WIDTH, RetroManiaGame.V_HEIGHT, new OrthographicCamera());
  UserRenderPreference userRenderPreference;

  @Inject
  IntroductoryScreen(
//      TiledMapIndividualFactory tiledMapIndividualFactory,
      UserRenderPreference userRenderPreference) {
    this.userRenderPreference = userRenderPreference;
//    this.tiledMapIndividualFactory = tiledMapIndividualFactory;
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
                    userRenderPreference)
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
