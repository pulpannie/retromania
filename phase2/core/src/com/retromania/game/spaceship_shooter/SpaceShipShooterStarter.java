package com.retromania.game.spaceship_shooter;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
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
import com.retromania.game.spaceship_shooter.screens.SettingScreen;
import com.retromania.game.spaceship_shooter.screens.StateFactory;

import java.util.List;

public class SpaceShipShooterStarter extends RetroManiaInnerGame implements MainScreenInterface {


    static GameStats gameStats;

    static String theme = "Independence Day";

    public static String getTheme() {
        return theme;
    }

    public static GameStats getGameStats() {
        return gameStats;
    }

    public static Screen getPlayScreen() {
        return playScreen;
    }

    public static Screen getPauseScreen() {
        return pauseScreen;
    }

    public static Screen getMenuScreen() {
        return menuScreen;
    }

    public static Screen getSettingScreen() {
        return settingScreen;
    }

    public static void setPlayScreen(PlayScreen playScreen) {
        SpaceShipShooterStarter.playScreen = playScreen;
    }

    static Screen playScreen;
    static Screen pauseScreen;
    static Screen menuScreen;
    static Screen settingScreen;


    private Preferences preferences;

    public SpaceShipShooterStarter(RetroManiaGame game){
        super("Spaceship shooter", RetroManiaGame.Orientation.VERTICAL);
        gameStats = new GameStats();
        playScreen = StateFactory.getScreen("play screen", this);
        pauseScreen = StateFactory.getScreen("pause screen", this);
        menuScreen = StateFactory.getScreen("menu screen",  this);
        settingScreen = StateFactory.getScreen("setting screen",  this);
        preferences = game.getPrefrences(Configuration.spaceshipDestroyerPreference);
    }


    @Override
    public void setCurrentUser(String name) {
        this.currentUser = new RetroManiaGeneralUser(name);
        if (preferences.contains(currentUser.getUserName())){
            this.currentUser.setScore(preferences.getInteger(currentUser.getUserName()));
        }
    }
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
        game.setScreen(pauseScreen);
    }

    @Override
    public void resume() {
        game.setScreen(playScreen);
    }

    @Override
    public void modify() {
        game.setScreen(settingScreen);
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public void restart(){
        setPlayScreen(new PlayScreen(this));
        game.setScreen(getPlayScreen());
    }

    public void returnMenu(String theme){
        game.setScreen(menuScreen);
        this.theme = theme;
    }

    public User getUser(){return currentUser;}
}
