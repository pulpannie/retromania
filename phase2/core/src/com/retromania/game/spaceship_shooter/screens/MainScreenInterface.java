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
    public void pause();

    /**
     * resume the screen.
     */
    public void resume();

    /**
     * restart the screen.
     */
    public void restart();

    /**
     * go to setting screen.
     */
    public void modify();

    /**
     * go to menu screen.
     */
    public void returnMenu(String theme);

    /**
     * get the user of the screen.
     */
    public User getUser();

    /**
     * save the screen.
     */
    public void save(Object... args);
}
