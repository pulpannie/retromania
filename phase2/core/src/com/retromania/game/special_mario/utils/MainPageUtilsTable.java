package com.retromania.game.special_mario.utils;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.retromania.game.shared_abstractions.ButtonMaker;
import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.special_mario.models.utils.LevelPreference;
import com.retromania.game.special_mario.views.menu.IntroductoryScreen;
import com.retromania.game.special_mario.views.menu.MenuScreen;
import com.retromania.game.special_mario.views.mission.first.FirstMissionView;
import com.retromania.game.special_mario.views.mission.seccond.SecondMissionView;
import com.retromania.game.special_mario.views.renderables.UserRenderPreference;

public class MainPageUtilsTable {

  private Table table;
  private Button gameStartButton;
  private Button settingButton;
  private MenuScreen menuScreen;

  private UserRenderPreference userRenderPreference;
  private LevelPreference levelPreference;
  private IntroductoryScreen introductoryScreen;

  private RetroManiaGame game;

  private FirstMissionView firstMissionView;
  private SecondMissionView secondMissionView;

  public MainPageUtilsTable(
          String gameStartString,
          String settingString,
          MenuScreen menuScreen,
          IntroductoryScreen introductoryScreen,
          LevelPreference levelPreference,
          UserRenderPreference userRenderPreference,
          RetroManiaGame game,
          FirstMissionView firstMissionView,
          SecondMissionView secondMissionView) {
    this.levelPreference = levelPreference;
    this.userRenderPreference = userRenderPreference;
    this.menuScreen = menuScreen;
    this.introductoryScreen = introductoryScreen;
    this.game = game;
    this.firstMissionView = firstMissionView;
    this.secondMissionView = secondMissionView;
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
        userRenderPreference.start();
        menuScreen.dispose();
        introductoryScreen.dispose();
        if (levelPreference.isItFirstMission()) game.setScreen(firstMissionView);
        else game.setScreen(secondMissionView);
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
