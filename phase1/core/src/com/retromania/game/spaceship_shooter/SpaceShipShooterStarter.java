package com.retromania.game.spaceship_shooter;

import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.shared_abstractions.RetroManiaGeneralUser;
import com.retromania.game.shared_abstractions.RetroManiaInnerGame;
import com.retromania.game.spaceship_shooter.individuals.GameStats;
import com.retromania.game.spaceship_shooter.screens.MenuScreen;
import com.retromania.game.spaceship_shooter.screens.PauseScreen;
import com.retromania.game.spaceship_shooter.screens.PlayScreen;

import java.util.List;

public class SpaceShipShooterStarter extends RetroManiaInnerGame {


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

    public SpaceShipShooterStarter(RetroManiaGame game){
        super(game, "Spaceship shooter", RetroManiaGame.Orientation.HORIZONTAL);
        gameStats = new GameStats();
        playScreen = new PlayScreen(game);
        pauseScreen = new PauseScreen(game);
        menuScreen = new MenuScreen(game);
    }


    //TODO Override setCurrentUser : Which sets your user and you should use this for checking whether or not you have a personal best
    @Override
    public void setCurrentUser(String name) {
        this.currentUser = new RetroManiaGeneralUser(name);
    }
    //	TODO Override setBestUser : this is where you should try and retrieve information for your best user, look at save and retrieve functions
    @Override
    public void setBestUser() {
        this.bestUser = new RetroManiaGeneralUser("POR");
    }

    @Override
    public Integer getBestUserScore() {
        return 0;
    }

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
}
