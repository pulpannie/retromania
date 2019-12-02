package com.retromania.game.mario.views.menu;

import com.retromania.game.shared_abstractions.RetroManiaScreen;

/**
 *
 * The menu that the user sees
 *
 * */
abstract class MenuOptionScreen extends RetroManiaScreen {
    MenuScreen menuScreen;
    void setMenuPage(MenuScreen menuScreen){
        this.menuScreen = menuScreen;
    }
}
