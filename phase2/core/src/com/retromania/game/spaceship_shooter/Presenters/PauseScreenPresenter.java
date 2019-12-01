package com.retromania.game.spaceship_shooter.Presenters;

import com.retromania.game.spaceship_shooter.Views.MainScreenInterface;

public class PauseScreenPresenter extends Presenter {

    private MainScreenInterface mainScreen;

    public PauseScreenPresenter(MainScreenInterface mainScreen){
        super();
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
