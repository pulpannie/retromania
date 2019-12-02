package com.retromania.game.mario.views.menu;

import android.annotation.SuppressLint;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.retromania.game.shared_abstractions.ButtonMaker;
import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.mario.models.utils.LevelPreference;
import com.retromania.game.mario.views.mission.first.FirstMissionView;
import com.retromania.game.mario.views.mission.seccond.SecondMissionView;
import com.retromania.game.mario.views.renderables.UserRenderPreference;

import java.util.function.Supplier;

import javax.inject.Inject;
import javax.inject.Singleton;


/**
 *
 * The setting screen inside our menu screen
 *
 * */
@Singleton
public class SettingScreen extends MenuOptionScreen {
  private Stage stage;

  private UserRenderPreference userRenderPreference;
  private LevelPreference levelPreference;

  private FirstMissionView firstMissionView;
  private SecondMissionView secondMissionView;

  @Inject
  SettingScreen(
      UserRenderPreference userRenderPreference,
      LevelPreference levelPreference,
      FirstMissionView firstMissionView,
      SecondMissionView secondMissionView) {
    this.levelPreference = levelPreference;
    this.userRenderPreference = userRenderPreference;
    this.firstMissionView = firstMissionView;
    this.secondMissionView = secondMissionView;
    gamePort =
        new FitViewport(RetroManiaGame.V_WIDTH, RetroManiaGame.V_HEIGHT, new OrthographicCamera());
  }

  @Override
  public void handleInput() {}

  @Override
  public void show() {
    stage = new Stage(gamePort, game.sb);
    Table table = setUpTable();
    table.debug();
    setUpGameLevelOptions(table);
    setUpLevelSetting(table);
    setUpGoBack(table);
    Gdx.input.setInputProcessor(stage);
  }

  private Table setUpTable() {
    Table table = new Table();
    stage.addActor(table);
    table.center();
    calibrateTable(table);
    return table;
  }

  private Table setUpTable(Table outterTable) {
    Table table = new Table();
    table.top();
    outterTable.add(table).padTop(30).expandX().row();
    calibrateTable(table, outterTable);
    return table;
  }

  private void calibrateTable(Table table) {
    table.setFillParent(true);
    table.pack();
  }

  private void calibrateTable(Table table, Table outterTable) {
    table.setWidth(outterTable.getWidth());
  }

  private static TextButton makeTextButton(
      String data, Color color, float scaleFont, float scaleButton) {
    BitmapFont f = new BitmapFont();
    f.getData().setScale(scaleFont);
    TextButton.TextButtonStyle style = ButtonMaker.makeButton("").getStyle();
    style.font = f;
    style.fontColor = color;
    TextButton textButton = new TextButton(data, style);
    textButton.scaleBy(scaleButton);
    return textButton;
  }

  private void setUpGameLevelOptions(Table outterTable) {
    Table table = setUpTable(outterTable);
    Cell c = null;
    for (Supplier f : userRenderPreference.getRenderModeFunctions().keySet()) {
      String s = userRenderPreference.getRenderModeFunctions().get(f);
      TextButton textButton = makeTextButton(s, Color.RED, .8f, .8f);
      textButton.addListener(
          new ClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void clicked(InputEvent event, float x, float y) {
              f.get();
            }
          });
      c =
          addPortionTextButtonToTable(
              table,
              textButton,
              0,
              table.getWidth() / userRenderPreference.getRenderModeFunctions().size() + .1f);
    }
    c.row();
  }

  private void setUpGoBack(Table outterTable) {
    Table table = setUpTable(outterTable);
    TextButton textButton = makeTextButton("Play Our Game", Color.GREEN, 1, 1);
    textButton.addListener(
        new ClickListener() {
          @Override
          public void clicked(InputEvent event, float x, float y) {
            userRenderPreference.start();
            dispose();
            if (levelPreference.isItFirstMission()) game.setScreen(firstMissionView);
            else game.setScreen(secondMissionView);
          }
        });
    addPortionTextButtonToTable(table, textButton, 0, table.getWidth()).row();
  }

  private void setUpLevelSetting(Table outterTable) {
    Table table = setUpTable(outterTable);
    Cell c = null;
    for (Supplier f : levelPreference.getLevelModeFunctions().keySet()) {
      String s = levelPreference.getLevelModeFunctions().get(f);
      TextButton textButton = makeTextButton(s, Color.RED, 1, 1);
      textButton.addListener(
          new ClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void clicked(InputEvent event, float x, float y) {
              f.get();
            }
          });
      c =
          addPortionTextButtonToTable(
              table,
              textButton,
              0,
              table.getWidth() / levelPreference.getLevelModeFunctions().size());
    }
    c.row();
  }

  private Cell addPortionTextButtonToTable(
      Table table, TextButton textButton, float padRight, float width) {
    return table.add(textButton).padRight(padRight).width(width);
  }

  @Override
  public void render(float delta) {
    stage.draw();
  }

  @Override
  public void resize(int width, int height) {
    gamePort.update(width, height);
  }

  @Override
  public void dispose() {
    stage.dispose();
  }
}
