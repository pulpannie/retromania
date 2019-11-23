package com.retromania.game.special_mario.utils;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retromania.game.shared_abstractions.ButtonMaker;

public class MainPageUtilsTable {

  private Table table;
  private Button gameStartButton;
  private Button settingButton;

  public MainPageUtilsTable(String gameStartString, String settingString) {
    makeGameStartButton(gameStartString);
    makeSettingButton(settingString);
    makeTable();
  }

  private void makeGameStartButton(String gameStartString) {
    gameStartButton = ButtonMaker.makeButton(gameStartString);
    gameStartButton.addListener(getGameStartButtonClickListener());
  }

  private ClickListener getGameStartButtonClickListener() {
    return new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        System.out.println("go to game");
      }
    };
  }

  private void makeSettingButton(String settingString) {
    settingButton = ButtonMaker.makeButton(settingString);
    settingButton.addListener(getSettingButtonClickListener());
  }

  private ClickListener getSettingButtonClickListener() {
    return new ClickListener() {
      @Override
      public void clicked(InputEvent event, float x, float y) {
        System.out.println("go to settings");
      }
    };
  }

  private void makeTable() {
    assert (settingButton != null);
    assert (gameStartButton != null);

    table = new Table();

    table.padTop(10);
    table.add(gameStartButton).row();
    table.add(settingButton).row();
  }

  public Table getTable() {
    return table;
  }
}
