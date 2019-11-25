package com.retromania.game.tic_tac_toe;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.retromania.game.shared_abstractions.Configuration;
import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.shared_abstractions.RetroManiaGeneralUser;
import com.retromania.game.shared_abstractions.RetroManiaInnerGame;
import com.retromania.game.shared_abstractions.User;
import com.retromania.game.tic_tac_toe.screens.MenuScreen;

import java.util.List;

public class TicTacToeStarter extends RetroManiaInnerGame {
    public Stage stage;
    public SpriteBatch batch;
    private MenuScreen menuScreen = new MenuScreen();
    private Preferences preferences;


    public TicTacToeStarter(String name, RetroManiaGame.Orientation orientation) {
        super(name, orientation);
        preferences = game.getPrefrences(Configuration.tictactoePreference);

    }

    @Override
    public void handleInput() {

    }

    @Override
    public void show() {
        game.setScreen(menuScreen);
    }

  @Override
  public void render(float delta) {

    }


    //TODO Override setCurrentUser : Which sets your user and you should use this for checking whether or not you have a personal best
    @Override
    public void setCurrentUser(String name) {
        preferences = game.getPrefrences(Configuration.tictactoePreference);
        this.currentUser = new RetroManiaGeneralUser(name);
        this.currentUser.setScore(preferences.getInteger(currentUser.getUserName()));
    }
    //	TODO Override setBestUser : this is where you should try and retrieve information for your best user, look at save and retrieve functions
    @Override
    public void setBestUser() {
        preferences = game.getPrefrences(Configuration.tictactoePreference);
        User user = new RetroManiaGeneralUser(preferences.getString(Configuration.bestUserUserName));
        user.setScore(preferences.getInteger(Configuration.bestUserScore));
        bestUser = user;
    }

    @Override
    public Integer getBestUserScore() {
        return bestUser.getScore();
    }


    @Override
    public void save(Object... args) {
        if(!preferences.contains("best player name") || currentUser.getScore() > bestUser.getScore()){
            gameSaver.saveBestUser(currentUser);
        }
        else if (!preferences.contains(currentUser.getUserName()) || currentUser.getScore() > preferences.getInteger(currentUser.getUserName())){
            gameSaver.saveNormalUser(currentUser);
        }

        preferences.flush();
    }

    @Override
    public List<Object> retrieve() {
        return null;
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
