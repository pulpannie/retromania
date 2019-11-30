package com.retromania.game.tic_tac_toe.presenters;

import com.retromania.game.RetroMania;
import com.retromania.game.tic_tac_toe.screens.MenuScreen;

public class StarterPresenter extends Presenter {
    MenuScreen menuScreen;

    public StarterPresenter(String screenType, MenuScreen menuScreen) {
        super(screenType);
        this.menuScreen = menuScreen;
    }

    public void returnMenuScreen(){
        RetroMania.getRetroManiaInstance().setScreen(menuScreen);
    }
}
