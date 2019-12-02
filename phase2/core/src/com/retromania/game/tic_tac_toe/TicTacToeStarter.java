package com.retromania.game.tic_tac_toe;

import com.retromania.game.RetroMania;
import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.shared_abstractions.RetroManiaInnerGame;
import com.retromania.game.tic_tac_toe.screens.MenuScreen;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * View for starting the TicTacToe game.
 * @author Hyokyung Kim.
 */
@Singleton
public class TicTacToeStarter extends RetroManiaInnerGame {
    static final String NAME_OF_GAME = "Tic Tac Toe";
    private MenuScreen menuScreen;

    /**
     * getter for the NameOfGame.
     * @return NameOfGame.
     */
    public static String getNameOfGame(){
        return NAME_OF_GAME;
    }

    /**
     * @param menuScreen injected through dagger.
     */
    @Inject
    public TicTacToeStarter(MenuScreen menuScreen) {
        super(NAME_OF_GAME, RetroManiaGame.Orientation.VERTICAL);
        this.menuScreen = menuScreen;
    }

    @Override
    public void handleInput() {

    }

    /**
     * shows the screen.
     */
    @Override
    public void show() { RetroMania.getRetroManiaInstance().setScreen(menuScreen);}

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
