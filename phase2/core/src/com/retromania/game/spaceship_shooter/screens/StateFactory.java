package com.retromania.game.spaceship_shooter.screens;

import com.badlogic.gdx.Screen;
import com.retromania.game.shared_abstractions.RetroManiaGame;

public class StateFactory {

    public static Screen getScreen(String type, MainScreenInterface mainscreen){
        if (type.equalsIgnoreCase("play screen"))
            return new PlayScreen(mainscreen);
        else if (type.equalsIgnoreCase("pause screen"))
            return new PauseScreen(mainscreen);
        else if (type.equalsIgnoreCase("menu screen"))
            return new MenuScreen(mainscreen);
        else if (type.equalsIgnoreCase("setting screen"))
            return new SettingScreen(mainscreen);
        else
            return null;
    }
}
