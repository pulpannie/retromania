package com.retromania.game.special_mario.utils;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retromania.game.shared_abstractions.ButtonMaker;
import com.retromania.game.special_mario.views.menu.MenuScreen;
import com.retromania.game.special_mario.views.renderables.UserRenderPreference;

public class MainPageUtilsTable {

  private Table table;
  private Button gameStartButton;
  private Button settingButton;
  private MenuScreen menuScreen;
  private UserRenderPreference userRenderPreference;

  public MainPageUtilsTable(
          String gameStartString,
          String settingString,
          MenuScreen menuScreen,
          UserRenderPreference userRenderPreference) {
    this.userRenderPreference = userRenderPreference;
    this.menuScreen = menuScreen;
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
        menuScreen.gotoSettingScreen();
      }
    };
  }

  private void makeTable() {
    assert (settingButton != null);
    assert (gameStartButton != null);

    table = new Table();

    table.padTop(10);
    table.add(gameStartButton).pad(10).row();
    table.add(settingButton).pad(10).row();
  }

  public Table getTable() {
    return table;
  }
}
