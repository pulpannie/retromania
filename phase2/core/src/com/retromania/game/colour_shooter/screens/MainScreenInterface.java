package com.retromania.game.colour_shooter.screens;

import com.retromania.game.shared_abstractions.User;

public interface MainScreenInterface {
    public void pause();
    public void resume();
    public void restart();
    public User getUser();
    public void save(Object... args);
}

