package com.retromania.game.shared_abstractions;

import com.badlogic.gdx.Input;

public class UserNameTextInputListener implements Input.TextInputListener {

    private String userName;
    private RetroManiaGame game;
    private RetroManiaInnerGame selectedGame;

    public  UserNameTextInputListener(RetroManiaGame game, RetroManiaInnerGame selectedGame){
        this.game = game;
        this.selectedGame = selectedGame;
    }

    @Override
    public void input(String userName) {
        this.userName = userName;
        if(!this.userName.equals("")&&this.userName.length()<=3){
            game.setOrientation(selectedGame.getOrientation());
            selectedGame.setCurrentUser(this.userName);
            game.setScreen(selectedGame);
        }
    }

    @Override
    public void canceled() {

    }
}
