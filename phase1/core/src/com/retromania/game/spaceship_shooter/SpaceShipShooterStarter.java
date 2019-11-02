package com.retromania.game.spaceship_shooter;

import com.badlogic.gdx.Preferences;
import com.retromania.game.shared_abstractions.Configuration;
import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.shared_abstractions.RetroManiaGeneralUser;
import com.retromania.game.shared_abstractions.RetroManiaInnerGame;
import com.retromania.game.shared_abstractions.User;
import com.retromania.game.spaceship_shooter.individuals.GameStats;
import com.retromania.game.spaceship_shooter.screens.MainScreenInterface;
import com.retromania.game.spaceship_shooter.screens.MenuScreen;
import com.retromania.game.spaceship_shooter.screens.PauseScreen;
import com.retromania.game.spaceship_shooter.screens.PlayScreen;

import java.util.List;

public class SpaceShipShooterStarter extends RetroManiaInnerGame implements MainScreenInterface {


    static GameStats gameStats;

    public static GameStats getGameStats() {
        return gameStats;
    }

    public static PlayScreen getPlayScreen() {
        return playScreen;
    }

    public static PauseScreen getPauseScreen() {
        return pauseScreen;
    }

    public static MenuScreen getMenuScreen() {
        return menuScreen;
    }

    public static void setPlayScreen(PlayScreen playScreen) {
        SpaceShipShooterStarter.playScreen = playScreen;
    }

    static PlayScreen playScreen;
    static PauseScreen pauseScreen;
    static MenuScreen menuScreen;


    private Preferences preferences;

    public SpaceShipShooterStarter(RetroManiaGame game){
        super(game, "Spaceship shooter", RetroManiaGame.Orientation.HORIZONTAL);
        gameStats = new GameStats();
        playScreen = new PlayScreen(game, this);
        pauseScreen = new PauseScreen(game, this);
        menuScreen = new MenuScreen(game, this);
        preferences = game.getPrefrences(Configuration.spaceshipDestroyerPreference);
    }


    //TODO Override setCurrentUser : Which sets your user and you should use this for checking whether or not you have a personal best
    @Override
    public void setCurrentUser(String name) {
        this.currentUser = new RetroManiaGeneralUser(name);
        if (preferences.contains(currentUser.getUserName())){
            this.currentUser.setScore(preferences.getInteger(currentUser.getUserName()));
        }
    }
    //	TODO Override setBestUser : this is where you should try and retrieve information for your best user, look at save and retrieve functions
    @Override
    public void setBestUser() {
        preferences = game.getPrefrences(Configuration.spaceshipDestroyerPreference);
        if (preferences.contains("best player name")){
            this.bestUser = new RetroManiaGeneralUser(preferences.getString("best player name"));
            this.bestUser.setScore(preferences.getInteger(bestUser.getUserName()));
        }
        else{
            this.bestUser = new RetroManiaGeneralUser("");
        }
    }

    @Override
    public Integer getBestUserScore() {
        return bestUser.getScore();
    }

    public void save(Object... args) {
        if(!preferences.contains("best player name") || currentUser.getScore() > bestUser.getScore()){
            preferences.putString("best player name", this.currentUser.getUserName());
            preferences.putInteger(this.currentUser.getUserName(), this.currentUser.getScore());
        }
        else if (!preferences.contains(currentUser.getUserName()) || currentUser.getScore() > preferences.getInteger(currentUser.getUserName())){
            preferences.putInteger(this.currentUser.getUserName(), this.currentUser.getScore());
        }

        preferences.flush();

    }

    @Override
    public List<Object> retrieve() { return null; }

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

    public void restart(){}

    public User getUser(){return currentUser;}
}
