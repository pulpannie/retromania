package com.retromania.game.special_mario.views.menu;

import com.retromania.game.shared_abstractions.RetroManiaScreen;

public abstract class MenuOptionScreen extends RetroManiaScreen {
    MenuScreen menuScreen;
    public void setMenuPage(MenuScreen menuScreen){
        this.menuScreen = menuScreen;
    }
}
