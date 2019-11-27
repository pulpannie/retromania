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

  private void changeScreen(RetroManiaScreen newScreen){
      selectedScreen.dispose();
      selectedScreen = newScreen;
      selectedScreen.show();
  }
  public void gotoIntroductoryScreen(){
      changeScreen(new IntroductoryScreen(this));
  }
  public void gotoSettingScreen(){
      changeScreen(new SettingScreen(this));
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
