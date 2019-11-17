package com.retromania.game.spaceship_shooter.screens;

import com.badlogic.gdx.Screen;
import com.retromania.game.shared_abstractions.RetroManiaGame;

public class StateFactory {

    public static Screen getScreen(String type, RetroManiaGame game, MainScreenInterface mainscreen){
        if (type.equalsIgnoreCase("play screen"))
            return new PlayScreen(game, mainscreen);
        else if (type.equalsIgnoreCase("pause screen"))
            return new PauseScreen(game, mainscreen);
        else if (type.equalsIgnoreCase("menu screen"))
            return new MenuScreen(game, mainscreen);

        else
            return null;
    }
}