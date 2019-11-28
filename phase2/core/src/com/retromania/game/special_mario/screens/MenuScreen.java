package com.retromania.game.special_mario.screens;

import com.retromania.game.shared_abstractions.RetroManiaScreen;
import com.retromania.game.special_mario.utils.WorldLoader;

import javax.inject.Inject;

public class MenuScreen extends RetroManiaScreen {

  private MenuOptionScreen selectedScreen;
  private final IntroductoryScreen introductoryScreen;
  private final SettingScreen settingScreen;
  private WorldLoader worldLoader;

  @Inject
  public MenuScreen(
      IntroductoryScreen introductoryScreen, SettingScreen settingScreen, WorldLoader worldLoader) {
    this.introductoryScreen = introductoryScreen;
    this.settingScreen = settingScreen;
    this.worldLoader = worldLoader;
    gotoIntroductoryScreen();
  }

  @Override
  public void handleInput() {}

  @Override
  public void show() {
    selectedScreen.show();
  }

  private void changeScreen(MenuOptionScreen newScreen) {
    selectedScreen = newScreen;
    selectedScreen.setMenuPage(this);
    selectedScreen.show();
  }

  public void gotoIntroductoryScreen() {
    changeScreen(introductoryScreen);
  }

  public void gotoSettingScreen() {
    changeScreen(settingScreen);
  }

  @Override
  public void render(float delta) {
    selectedScreen.render(delta);
  }

  @Override
  public void resize(int width, int height) {
    selectedScreen.resize(width, height);
  }

  @Override
  public void dispose() {}
}
