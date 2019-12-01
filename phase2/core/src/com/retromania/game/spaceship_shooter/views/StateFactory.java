package com.retromania.game.spaceship_shooter.views;

import com.badlogic.gdx.Screen;

/**
 * The State Factory that is responsible for creating an instance of a screen. This class is created
 * to encapsulate constructor calls to get instances of the screen without the rest of the program
 * knowing which constructor was called
 *
 * @author Thuy, Umid.
 */
public class StateFactory {

  /**
   * Return an instance of the screen with the input type.
   *
   * @param type the string represents the type of the screen that the Factory will generate an
   *     instance.
   * @param mainScreen access to viewport of starter class through interface
   * @return an instance of the screen with the input type.
   */
  public static Screen getScreen(String type, MainScreenInterface mainScreen) {
    if (type.equalsIgnoreCase("play screen")) return new PlayScreen(mainScreen);
    else if (type.equalsIgnoreCase("pause screen")) return new PauseScreen(mainScreen);
    else if (type.equalsIgnoreCase("menu screen")) return new MenuScreen(mainScreen);
    else if (type.equalsIgnoreCase("setting screen")) return new SettingScreen(mainScreen);
    else return null;
  }
}
