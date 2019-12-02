package com.retromania.game.spaceship_shooter.presenters;

import com.retromania.game.spaceship_shooter.views.MainScreenInterface;

/**
 * Presenter class of PauseScreen. It has access to models of PauseScreen and updates models by
 * command of view class
 *
 * @author Umid, Thuy
 */
public class PauseScreenPresenter extends Presenter {
  /** Interface to give input to viewport of starter class */
  private MainScreenInterface mainScreen;

  /**
   * Constructor for the presenter
   *
   * @param mainScreen access to viewport of starter class through interface
   */
  public PauseScreenPresenter(MainScreenInterface mainScreen) {
    super();
    this.mainScreen = mainScreen;
  }

  /** Changes screen from Pause to Resume */
  public void resume() {
    mainScreen.resume();
  }

  /** Changes screen from Pause to *New* Play */
  public void restart() {
    mainScreen.restart();
  }

  /** Changes screen from Pause to Settings */
  public void modify() {
    mainScreen.modify();
  }

  /** Updates models by request of View/MenuScreen */
  public void update(float dt) {
    super.update(dt);
  }
}
