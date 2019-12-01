package com.retromania.game.spaceship_shooter.Presenters;

import com.retromania.game.spaceship_shooter.Views.MainScreenInterface;

public class SettingsScreenPresenter extends Presenter {

    private MainScreenInterface mainScreen;

    public SettingsScreenPresenter(MainScreenInterface mainScreen){
        super();
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