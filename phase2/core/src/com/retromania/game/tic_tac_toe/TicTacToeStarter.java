package com.retromania.game.tic_tac_toe;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.shared_abstractions.RetroManiaInnerGame;
import com.retromania.game.shared_abstractions.User;
import com.retromania.game.tic_tac_toe.screens.MenuScreen;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class TicTacToeStarter extends RetroManiaInnerGame {
    public Stage stage;
    private MenuScreen menuScreen;


    @Inject
    public TicTacToeStarter(MenuScreen menuScreen) {
        super("Tic Tac Toe", RetroManiaGame.Orientation.VERTICAL);
        this.menuScreen = menuScreen;
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
