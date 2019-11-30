package com.retromania.game.spaceship_shooter.screens;

import com.badlogic.gdx.Screen;

public class StateFactory {

    public static Screen getScreen(String type, MainScreenInterface mainScreen){
        if (type.equalsIgnoreCase("play screen"))
            return new PlayScreen(mainScreen);
        else if (type.equalsIgnoreCase("pause screen"))
            return new PauseScreen(mainScreen);
        else if (type.equalsIgnoreCase("menu screen"))
            return new MenuScreen(mainScreen);
        else if (type.equalsIgnoreCase("setting screen"))
            return new SettingScreen(mainScreen);
        else
            return null;
    }
}
