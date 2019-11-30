package com.retromania.game.spaceship_shooter.presenters;

import com.retromania.game.spaceship_shooter.screens.MainScreenInterface;

public class PauseScreenPresenter extends Presenter {

    private MainScreenInterface mainScreen;

    public PauseScreenPresenter(String screenType, MainScreenInterface mainScreen){
        super(screenType);
        this.mainScreen = mainScreen;
    }

    public void resume() {
        mainScreen.resume();
    }

    public void restart() {
        mainScreen.restart();
    }

    public void modify() {
        mainScreen.modify();
    }

    public void dispose() {

    }

    public void update(float dt){
        super.update(dt);
    }
}
