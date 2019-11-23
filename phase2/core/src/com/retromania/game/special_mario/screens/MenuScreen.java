package com.retromania.game.special_mario.screens;

import com.retromania.game.shared_abstractions.RetroManiaScreen;

public class MenuScreen extends RetroManiaScreen {


  private RetroManiaScreen selectedScreen;

  @Override
  public void handleInput() {}

  @Override
  public void show() {
    selectedScreen = new IntroductoryScreen(this);
    selectedScreen.show();
  }

  public void gotoIntroductoryScreen(){
      selectedScreen.dispose();
      selectedScreen = new IntroductoryScreen(this);
      selectedScreen.show();
  }
  public void gotoSettingScreen(){
      selectedScreen.dispose();
      selectedScreen = new SettingScreen(this);
      selectedScreen.show();
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
