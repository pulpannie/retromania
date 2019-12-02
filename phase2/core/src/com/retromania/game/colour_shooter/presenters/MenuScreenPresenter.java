package com.retromania.game.colour_shooter.presenters;

import com.retromania.game.colour_shooter.views.MainScreenInterface;

public class MenuScreenPresenter extends Presenter{
    private MainScreenInterface mainScreen;

    public MenuScreenPresenter(String screenType, MainScreenInterface mainScreen) {
        super(screenType);
        this.mainScreen = mainScreen;
    }
    public void dispose() {

    }
    public void update(float dt) {super.update(dt);}
    public void start() {}

}
