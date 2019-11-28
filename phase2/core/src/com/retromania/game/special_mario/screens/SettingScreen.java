package com.retromania.game.special_mario.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.retromania.game.shared_abstractions.ButtonMaker;
import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.shared_abstractions.RetroManiaScreen;

import javax.inject.Inject;

public class SettingScreen extends MenuOptionScreen {
  private Stage stage;

  @Inject
  SettingScreen() {
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
    setUpGameOptions(table);
    setUpLevel(table);
    //        table.padTop(10);
    //        table.add(ButtonMaker.makeButton("c")).pad(10).expandX().row();
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
    outterTable.add(table).expandX().row();
    calibrateTable(table, outterTable);
    return table;
  }

  private void calibrateTable(Table table) {
    table.setFillParent(true);
    table.pack();
  }
  private void calibrateTable(Table table, Table outterTable) {
    table.setWidth(outterTable.getWidth());
    System.out.println(table.getWidth());
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

  private static TextButton makeTextButton(String data, Color color) {
    return makeTextButton(data, color, 1, 1);
  }

  private void setUpGameOptions(Table outterTable) {
    Table table = setUpTable(outterTable);
    table
        .add(makeTextButton("SURVIVAL : ON", Color.RED, 0.8f, .8f))
        .padRight(10)
        .width(table.getWidth() / 3.1f);
    table
        .add(makeTextButton("GHOST : OFF", Color.BLUE, 0.8f, 0.8f))
        .padRight(10)
        .width(table.getWidth() / 3.1f);
    table
        .add(makeTextButton("Normal : ON", Color.BLUE, 0.8f, 0.8f))
        .padRight(10)
        .width(table.getWidth() / 3.1f)
        .row();
  }

  private void setUpLevel(Table outterTable) {
    //        table.debug();
    Table table = setUpTable(outterTable);
    table.add(makeTextButton("Level 1", Color.RED)).width(table.getWidth() / 2f).expandX();
    table.add(makeTextButton("Level 2", Color.BLUE)).width(table.getWidth() / 2f).expandX().row();
    //        table.add(makeTextButton("Normal : ON", Color.RED, 0.8f,
    // 0.8f)).padRight(10).width(table.getWidth()/3.1f).row();
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
