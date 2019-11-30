package com.retromania.game.spaceship_shooter.screens;

import com.retromania.game.shared_abstractions.User;

/**
 * The MainScreenInterface is created to reduce coupling between
 * the Screen and its subclasses.
 * @author Thuy, Umid.
 */
public interface MainScreenInterface {
    /**
     * pause the screen
     */
    void pause();

    /**
     * resume the screen.
     */
    void resume();

    /**
     * restart the screen.
     */
    void restart();

    /**
     * go to setting screen.
     */
    void modify();

    /**
     * go to menu screen.
     */
    void returnMenu(String theme, boolean isMusic);

    void returnMenu();

    /**
     * get the user of the screen.
     */
    User getUser();

    /**
     * save the screen.
     */
    void save(Object... args);
}
