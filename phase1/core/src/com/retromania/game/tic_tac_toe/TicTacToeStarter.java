package com.retromania.game.tic_tac_toe;

import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.shared_abstractions.RetroManiaGeneralUser;
import com.retromania.game.shared_abstractions.RetroManiaInnerGame;
import com.retromania.game.shared_abstractions.User;
import com.retromania.game.spaceship_shooter.screens.MenuScreen;
import com.retromania.game.spaceship_shooter.screens.PlayScreen;

import java.util.List;

public class TicTacToeStarter extends RetroManiaInnerGame {
    static PlayScreen playScreen;
    static MenuScreen menuScreen;
    static GameOverScreen gameOverScreen;

    public TicTacToeStarter(RetroManiaGame game) {
        super(game, "Tic Tac Toe Game", RetroManiaGame.Orientation.VERTICAL);
        playScreen = new PlayScreen(game);
        gameOverScreen = new GameOverScreen(game, "winner");
    }

    public void setPlayScreen(PlayScreen screen){
        this.playScreen = screen;
    }

    //TODO Override setCurrentUser : Which sets your user and you should use this for checking whether or not you have a personal best
    @Override
    public void setCurrentUser(String name) {
        this.currentUser = new RetroManiaGeneralUser(name);
    }
    public User getCurrentUser() { return this.currentUser;}

    //	TODO Override setBestUser : this is where you should try and retrieve information for your best user, look at save and retrieve functions
    @Override
    public void setBestUser() {
        this.bestUser = new RetroManiaGeneralUser("POR");
    }

    @Override
    public Integer getBestUserScore() {
        return null;
    }


    @Override
    public void save(Object... args) {

    }

    @Override
    public List<Object> retrieve() {
        return null;
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void show() {
        game.setScreen(new TicTacToe(game));
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}


