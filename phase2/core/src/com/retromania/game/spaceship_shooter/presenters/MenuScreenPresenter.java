package com.retromania.game.spaceship_shooter.presenters;

import com.retromania.game.spaceship_shooter.screens.MainScreenInterface;

public class MenuScreenPresenter extends Presenter {

    private MainScreenInterface mainScreen;

    public MenuScreenPresenter(String screenType, MainScreenInterface mainScreen){
        super(screenType);
        this.mainScreen = mainScreen;
    }

    public void dispose() {

    }

    public void update(float dt){
        super.update(dt);
    }

    public void start() {
        mainScreen.restart();
    }
}
