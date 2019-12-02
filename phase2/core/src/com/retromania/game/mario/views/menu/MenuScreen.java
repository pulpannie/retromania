package com.retromania.game.mario.views.menu;

import com.retromania.game.shared_abstractions.RetroManiaScreen;

import javax.inject.Inject;

/**
 *
 * The implementation of the menu screen
 *
 * */
public class MenuScreen extends RetroManiaScreen {

  private MenuOptionScreen selectedScreen;
  private final IntroductoryScreen introductoryScreen;
  private final SettingScreen settingScreen;

  @Inject
  public MenuScreen(
      IntroductoryScreen introductoryScreen, SettingScreen settingScreen) {
    this.introductoryScreen = introductoryScreen;
    this.settingScreen = settingScreen;
  }

  @Override
  public void handleInput() {}

  @Override
  public void show() {
    gotoIntroductoryScreen();
    selectedScreen.show();
  }

  private void changeScreen(MenuOptionScreen newScreen) {
    if (selectedScreen != null){
      selectedScreen.dispose();
    }
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
