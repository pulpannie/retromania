package com.retromania.game.spaceship_shooter.Presenters;

import com.retromania.game.spaceship_shooter.Views.MainScreenInterface;

/**
 * Presenter class of SettingsScreen. It has access to models of SettingsScreen and updates models
 * by * command of view class
 *
 * @author Umid, Thuy
 */
public class SettingsScreenPresenter extends Presenter {
  /** Interface to give input to viewport of starter class */
  private MainScreenInterface mainScreen;

  /**
   * Constructor method for this class
   *
   * @param mainScreen access to viewport of starter class through interface
   */
  public SettingsScreenPresenter(MainScreenInterface mainScreen) {
    super();
    this.mainScreen = mainScreen;
  }

  /** Disposes the disposable objects */
  public void dispose() {}

  /** updates the models */
  public void update(float dt) {
    super.update(dt);
  }

  /**
   * Changes screen from Settings to Menu
   * @param theme chosen type of theme for game
   * @param isMusic boolean that keeps whether music should play or not
   */
  public void returnMenu(String theme, boolean isMusic) {
    mainScreen.returnMenu(theme, isMusic);
  }
}
