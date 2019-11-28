package com.retromania.game.special_mario.screens;

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
import com.retromania.game.special_mario.utils.TiledMapIndividualFactory;

import javax.inject.Inject;
import javax.inject.Singleton;

public class IntroductoryScreen extends MenuOptionScreen {

  private Stage stage;
  private Table table;
  TiledMapIndividualFactory tiledMapIndividualFactory;
  Viewport viewport2 =
      new FitViewport(RetroManiaGame.V_WIDTH, RetroManiaGame.V_HEIGHT, new OrthographicCamera());

  @Inject
  IntroductoryScreen(TiledMapIndividualFactory tiledMapIndividualFactory) {
    this.tiledMapIndividualFactory = tiledMapIndividualFactory;
  }

  @Override
  public void handleInput() {}

  @Override
  public void show() {

    stage = new Stage(viewport2, game.sb);
    table = new Table();
    table.setFillParent(true);
    Skin skin = new Skin(Gdx.files.internal("special_mario/ScaleFont.json"));
    Label brand = new Label("Our Special Mario", skin);
    brand.setFontScaleY(.4f);
    brand.setFontScaleX(.4f);
    brand.setColor(Color.RED);
    table.pack();
    table.top();
    table.setFillParent(true);
    table.padTop(10);
    table.add(brand).expandX().row();
    table.padTop(30);
    table
            .add(new MainPageUtilsTable(tiledMapIndividualFactory,"Play Game", "Settings", menuScreen).getTable())
            .expandX()
            .expandY()
            .row();
    stage.addActor(table);
    Gdx.input.setInputProcessor(stage);
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
