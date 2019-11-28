package com.retromania.game.tic_tac_toe;

import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.retromania.game.shared_abstractions.Configuration;
import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.shared_abstractions.RetroManiaGeneralUser;
import com.retromania.game.shared_abstractions.RetroManiaInnerGame;
import com.retromania.game.shared_abstractions.User;
import com.retromania.game.tic_tac_toe.screens.MenuScreen;

import java.util.List;

public class TicTacToeStarter extends RetroManiaInnerGame {
    public Stage stage;
    public SpriteBatch batch;
    private MenuScreen menuScreen;
    private Preferences preferences;
    private User currentUser;


    public TicTacToeStarter(String name, RetroManiaGame.Orientation orientation) {
        super(name, orientation);
        menuScreen = new MenuScreen(super.gameSaver);
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
