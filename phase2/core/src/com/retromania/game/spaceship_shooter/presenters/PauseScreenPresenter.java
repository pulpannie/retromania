package com.retromania.game.spaceship_shooter.presenters;

import com.retromania.game.spaceship_shooter.screens.MainScreenInterface;

public class PauseScreenPresenter extends Presenter {

    private MainScreenInterface mainscreen;

    public PauseScreenPresenter(String screenType, MainScreenInterface mainScreen){
        super(screenType);
        this.mainscreen = mainScreen;
    }

    public void resume() {
        mainscreen.resume();
    }

    public void restart() {
        mainscreen.restart();
    }

    public void modify() {
        mainscreen.modify();
    }

    public void dispose() {

    }

    public void update(float dt){
        super.update(dt);
    }
}
