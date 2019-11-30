package com.retromania.game.spaceship_shooter.presenters;

import com.retromania.game.spaceship_shooter.screens.MainScreenInterface;

public class SettingsScreenPresenter extends Presenter {

    private MainScreenInterface mainScreen;

    public SettingsScreenPresenter(String screenType, MainScreenInterface mainScreen){
        super(screenType);
        this.mainScreen = mainScreen;
    }

    public void dispose() {

    }

    public void update(float dt){
        super.update(dt);
    }

    public void returnMenu(String theme, boolean isMusic){
        mainScreen.returnMenu(theme, isMusic);
    }

}
