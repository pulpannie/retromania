package com.retromania.game.tic_tac_toe.presenters;

import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.retromania.game.tic_tac_toe.utils.ImageButtonBuilder;
import com.retromania.game.tic_tac_toe.screens.PlayScreen;
import com.retromania.game.tic_tac_toe.utils.UserPreference;

public class MenuPresenter{
    PlayScreen playScreen;
    ImageButtonBuilder imageButtonBuilder;
    static UserPreference userPreference = new UserPreference();


    public MenuPresenter(PlayScreen playScreen) {
        this.playScreen = playScreen;
        imageButtonBuilder = new ImageButtonBuilder();
    }

    public void reverseCat(){
        userPreference.setCat(!userPreference.getCat());
    }

    public void addSize(){
        userPreference.addSize();
    }

    public void decreaseSize(){
        userPreference.decreaseSize();
    }

    public int getGameSize(){
        return userPreference.getGameSize();
    }

}
