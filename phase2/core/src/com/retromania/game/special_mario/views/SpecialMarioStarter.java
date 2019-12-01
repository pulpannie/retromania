package com.retromania.game.special_mario.views;

import com.badlogic.gdx.Gdx;
import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.special_mario.models.player.MainPlayerInput;
import com.retromania.game.special_mario.presenter.SpecialMarioStarterPresenter;
import com.retromania.game.special_mario.utils.MusicManager;
import com.retromania.game.special_mario.views.menu.MenuScreen;
import com.retromania.game.special_mario.views.renderables.UserRenderPreference;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.retromania.game.special_mario.SpecialMarioConfiguration.GAME_NAME;

@Singleton
public class SpecialMarioStarter extends MarioView {

  private UserRenderPreference userRenderPreference;
  private MenuScreen menuScreen;
  private MusicManager musicManager;
  private SpecialMarioStarterPresenter specialMarioStarterPresenter;

  @Inject
  public SpecialMarioStarter(
      SpecialMarioStarterPresenter specialMarioStarterPresenter,
      UserRenderPreference userRenderPreference,
      MenuScreen menuScreen,
      MusicManager musicManager) {
    super(
        GAME_NAME,
        RetroManiaGame.Orientation.HORIZONTAL,
        userRenderPreference,
        specialMarioStarterPresenter);
    this.specialMarioStarterPresenter = specialMarioStarterPresenter;
    setUpMainWorldRenderer(userRenderPreference);
    this.menuScreen = menuScreen;
    this.musicManager = musicManager;
  }

  @Override
  public void handleInput() {
    setUpMainPlayerInput();
  }

  @Override
  public void update() {
    handleInput();
    updateModels();
    specialMarioStarterPresenter.present();
  }

  private void setUpMainPlayerInput() {
    specialMarioStarterPresenter.setMainPlayerInput(
        new MainPlayerInput(
            userRenderPreference.getRenderable().getGamePort().getScreenWidth(),
            userRenderPreference.getRenderable().getGamePort().getScreenHeight(),
            Gdx.input.getX(),
            Gdx.input.getY(),
            Gdx.input.justTouched(),
            Gdx.input.isTouched()));
  }

  @Override
  public void resize(int width, int height) {
    userRenderPreference.getRenderable().resize(width, height);
  }

  @Override
  public void show() {
    this.musicManager.play();
    setUpMenuScreen(menuScreen);
  }

  private void setUpMenuScreen(MenuScreen menuScreen) {
    this.menuScreen = menuScreen;
    addRenderable(this.menuScreen);
    this.menuScreen.show();
  }

  @Override
  public void render(float delta) {
    update();
    userRenderPreference.getRenderable().render(delta);
    menuScreen.render(delta);
  }

  private void setUpMainWorldRenderer(UserRenderPreference userRenderPreference) {
    this.userRenderPreference = userRenderPreference;
    this.userRenderPreference.getRenderable().setPresenter(specialMarioStarterPresenter);
  }
}
