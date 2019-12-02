package com.retromania.game.colour_shooter.screens;

import com.badlogic.gdx.Screen;
import com.retromania.game.colour_shooter.ColourShooterStarter;
import com.retromania.game.shared_abstractions.RetroManiaGame;

/**
 * The State Factory that is responsible for creating an instance of a screen.
 * This class is created to encapsulate constructor calls to get instances of the screen
 * without the rest of the program knowing which constructor was called.
 */
public class StateFactory {

    public static Screen getScreen(
            String type, RetroManiaGame game, ColourShooterStarter mainscreen) {
        if (type.equalsIgnoreCase("play screen")) return new PlayScreen(game, mainscreen);
        else if (type.equalsIgnoreCase("pause screen")) return new PauseScreen(game, mainscreen);
        else if (type.equalsIgnoreCase("menu screen")) return new MenuScreen(game, mainscreen);
        else if (type.equalsIgnoreCase("instructions screen")) return new InstructionsScreen(game, mainscreen);
        else return null;
    }
}
