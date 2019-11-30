package com.retromania.game.spaceship_shooter;

import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.shared_abstractions.RetroManiaInnerGame;
import com.retromania.game.shared_abstractions.User;
import com.retromania.game.spaceship_shooter.individuals.GameStats;
import com.retromania.game.spaceship_shooter.presenters.StarterPresenter;
import com.retromania.game.spaceship_shooter.screens.MainScreenInterface;
import com.retromania.game.utils.GameSaver;

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
    public static void playMusic(){StarterPresenter.playMusic();}
    public static String getTheme(){return StarterPresenter.getTheme();}
    public static GameStats getGameStats(){return StarterPresenter.getGameStats();}
    public void saveScore(int score){presenter.setScore(score);
    }

}
