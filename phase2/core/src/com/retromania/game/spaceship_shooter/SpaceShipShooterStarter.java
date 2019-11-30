package com.retromania.game.spaceship_shooter;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.retromania.game.shared_abstractions.Configuration;
import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.shared_abstractions.RetroManiaGeneralUser;
import com.retromania.game.shared_abstractions.RetroManiaInnerGame;
import com.retromania.game.shared_abstractions.User;
import com.retromania.game.spaceship_shooter.individuals.GameStats;
import com.retromania.game.spaceship_shooter.presenter.Presenter;
import com.retromania.game.spaceship_shooter.presenter.StarterPresenter;
import com.retromania.game.spaceship_shooter.screens.MainScreenInterface;
import com.retromania.game.spaceship_shooter.screens.MenuScreen;
import com.retromania.game.spaceship_shooter.screens.PauseScreen;
import com.retromania.game.spaceship_shooter.screens.PlayScreen;
import com.retromania.game.spaceship_shooter.screens.SettingScreen;
import com.retromania.game.spaceship_shooter.screens.StateFactory;
import com.retromania.game.spaceship_shooter.utils.MusicManager;

import java.util.List;

public class SpaceShipShooterStarter extends RetroManiaInnerGame implements MainScreenInterface {

    private StarterPresenter presenter;

    public SpaceShipShooterStarter(RetroManiaGame game){
        super("Spaceship shooter", RetroManiaGame.Orientation.VERTICAL);
        presenter = new StarterPresenter(this);
    }


    @Override
    public void handleInput() {

    }

    @Override
    public void show() { presenter.returnMenu();}

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        presenter.pause();
    }

    @Override
    public void resume() {
        presenter.resume();
    }

    @Override
    public void modify() {
        presenter.modify();
    }

    @Override
    public void returnMenu(String theme, boolean isMusic) {
        presenter.returnMenu(theme, isMusic);
    }

    public void returnMenu() { presenter.returnMenu();}

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public void restart(){
        presenter.restart(this);
    }

    public User getUser(){return currentUser;}

    public static void playMusic(){StarterPresenter.playMusic();}
    public static String getTheme(){return StarterPresenter.getTheme();}
    public static GameStats getGameStats(){return StarterPresenter.getGameStats();}

}
