package com.retromania.game.spaceship_shooter.Presenters;

import com.badlogic.gdx.Screen;
import com.retromania.game.RetroMania;
import com.retromania.game.spaceship_shooter.Models.GameStats;
import com.retromania.game.spaceship_shooter.Views.MainScreenInterface;
import com.retromania.game.spaceship_shooter.Views.PlayScreen;
import com.retromania.game.spaceship_shooter.Views.StateFactory;
import com.retromania.game.spaceship_shooter.Utils.MusicManager;
import com.retromania.game.utils.GameSaver;


public class StarterPresenter {
    private static GameStats gameStats;
    private static String theme = "Independence Day";
    private static boolean isMusic = false;
    private Screen playScreen;
    private Screen pauseScreen;
    private Screen menuScreen;
    private Screen settingScreen;
    private GameSaver gameSaver;

    public StarterPresenter(MainScreenInterface mainScreen){
        gameStats = new GameStats();
        playScreen = StateFactory.getScreen("play screen", mainScreen);
        pauseScreen = StateFactory.getScreen("pause screen", mainScreen);
        menuScreen = StateFactory.getScreen("menu screen",  mainScreen);
        settingScreen = StateFactory.getScreen("setting screen",  mainScreen);
        gameSaver = new GameSaver("Spaceship shooter");
    }

    public void pause() {
        RetroMania.getRetroManiaInstance().setScreen(pauseScreen);
    }

    public void resume() {
        RetroMania.getRetroManiaInstance().setScreen(playScreen);
    }


    public void modify() {
        RetroMania.getRetroManiaInstance().setScreen(settingScreen);
    }


    public void returnMenu(String theme, boolean isMusic) {
        StarterPresenter.theme = theme;
        StarterPresenter.isMusic = isMusic;
        RetroMania.getRetroManiaInstance().setScreen(menuScreen);
    }

    public void returnMenu() {
        RetroMania.getRetroManiaInstance().setScreen(menuScreen);
    }

    public void restart(MainScreenInterface mainScreen){
        playScreen = new PlayScreen(mainScreen);
        RetroMania.getRetroManiaInstance().setScreen(playScreen);
    }

    public static String getTheme() {
        return theme;
    }

    public static GameStats getGameStats() {
        return gameStats;
    }

    public static void playMusic() {
        if (isMusic)
            MusicManager.play();
        else
            MusicManager.stop();
    }

    public void setScore(int score){
        gameSaver.setScore(score);
    }
}
