package com.retromania.game.spaceship_shooter;

import com.retromania.game.shared_abstractions.RetroManiaGame;
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

    public void save(Object... args) {

    }

    @Override
    public List<Object> retreave() {
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
