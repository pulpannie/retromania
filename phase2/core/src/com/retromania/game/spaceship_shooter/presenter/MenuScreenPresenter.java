package com.retromania.game.spaceship_shooter.presenter;

import com.retromania.game.spaceship_shooter.screens.MainScreenInterface;

public class MenuScreenPresenter extends Presenter {

    private MainScreenInterface mainscreen;

    public MenuScreenPresenter(String screenType, MainScreenInterface mainScreen){
        super(screenType);
        this.mainscreen = mainScreen;
    }

    public void dispose() {

    }

    public void update(float dt){
        super.update(dt);
    }

    public void start() {
        mainscreen.restart();
    }
}
