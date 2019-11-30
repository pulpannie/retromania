package com.retromania.game.tic_tac_toe;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.retromania.game.shared_abstractions.Presentable;
import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.shared_abstractions.RetroManiaInnerGame;
import com.retromania.game.shared_abstractions.User;
import com.retromania.game.tic_tac_toe.presenters.StarterPresenter;
import com.retromania.game.tic_tac_toe.screens.MenuScreen;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TicTacToeStarter extends RetroManiaInnerGame {
    private StarterPresenter starterPresenter;
    static final String NAME_OF_GAME = "Tic Tac Toe";
    @Inject
    public TicTacToeStarter(MenuScreen menuScreen) {
        super(NAME_OF_GAME, RetroManiaGame.Orientation.VERTICAL);
        this.starterPresenter = new StarterPresenter("stretch", menuScreen);
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void show() {
        starterPresenter.returnMenuScreen();
    }

    @Override
    public void render(float delta) {

    }

    @Override
    public List<Object> retrieve() {
        return null;
    }

    @Override
    public void resize(int width, int height) {

    }

}
