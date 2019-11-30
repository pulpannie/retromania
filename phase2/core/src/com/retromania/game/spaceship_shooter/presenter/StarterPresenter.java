package com.retromania.game.spaceship_shooter.presenter;

import com.badlogic.gdx.Screen;
import com.retromania.game.RetroMania;
import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.spaceship_shooter.individuals.GameStats;
import com.retromania.game.spaceship_shooter.screens.MainScreenInterface;
import com.retromania.game.spaceship_shooter.screens.PlayScreen;
import com.retromania.game.spaceship_shooter.screens.StateFactory;
import com.retromania.game.spaceship_shooter.utils.MusicManager;


public class StarterPresenter {
    static GameStats gameStats;
    private static String theme = "Independence Day";
    private static boolean isMusic = false;
    private Screen playScreen;
    private Screen pauseScreen;
    private Screen menuScreen;
    private Screen settingScreen;

    public StarterPresenter(MainScreenInterface mainscreen){
        gameStats = new GameStats();
        playScreen = StateFactory.getScreen("play screen", mainscreen);
        pauseScreen = StateFactory.getScreen("pause screen", mainscreen);
        System.out.println("*********************");
        System.out.println(pauseScreen);
        menuScreen = StateFactory.getScreen("menu screen",  mainscreen);
        settingScreen = StateFactory.getScreen("setting screen",  mainscreen);
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

    public void restart(MainScreenInterface mainscreen){
        playScreen = new PlayScreen(mainscreen);
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


}
