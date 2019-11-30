package com.retromania.game.tic_tac_toe.presenters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.retromania.game.RetroMania;
import com.retromania.game.tic_tac_toe.individuals.Cell;
import com.retromania.game.tic_tac_toe.individuals.CellManager;
import com.retromania.game.tic_tac_toe.individuals.TicTacToe;
import com.retromania.game.tic_tac_toe.screens.GameOverScreen;
import com.retromania.game.tic_tac_toe.utils.UserPrefrence;

public class PlayPresenter extends Presenter {
    Texture cross, circle;
    UserPrefrence userPrefrence;
    TicTacToe ticTacToe;
    CellManager cellManager;
    public PlayPresenter(String screenType, UserPrefrence userPrefrence, CellManager cellManager) {
        super(screenType);
        this.userPrefrence = userPrefrence;
        this.cellManager = cellManager;
    }

    public String getWinner(){
        return ticTacToe.getWinner();
    }

    public void checkEnd(){
        if (isEnd()) {
            String winner = getWinner();
            if (winner.equals("None")) {
                RetroMania.getRetroManiaInstance().setScreen(new GameOverScreen(RetroMania.getRetroManiaInstance(), "No one"));
            } else if (winner.equals("Cross")) {
                //                currentUser = gameSaver.getCurrentUser();
                //                gameSaver.setScore(currentUser.getScore() + 1);
                RetroMania.getRetroManiaInstance().setScreen(new GameOverScreen(RetroMania.getRetroManiaInstance(), "Cross"));

            } else if (winner.equals("Circle")) {
                RetroMania.getRetroManiaInstance().setScreen(new GameOverScreen(RetroMania.getRetroManiaInstance(), "Circle"));
            }
        }
    }

    public boolean isEnd(){
        return ticTacToe.isEnd();
    }

    public void touchCells(float tmpx, float tmpy){
        int x = (int)((tmpx * userPrefrence.getGameSize())/Gdx.graphics.getWidth());
        int y = (int)((tmpy * userPrefrence.getGameSize())/Gdx.graphics.getHeight());
        ticTacToe.touchCell(x, y);
    }

    public Cell[][] getCells(){
        return ticTacToe.getCellStates();
    }

    public void createTicTacToe(){
        ticTacToe = new TicTacToe(userPrefrence, cellManager);

    }

    public void setTextures(){
        if (userPrefrence.getCat()) {
            cross = new Texture(Gdx.files.internal("tic_tac_toe/cat2.png"));
            circle = new Texture(Gdx.files.internal("tic_tac_toe/cat3.png"));
        } else {
            cross = new Texture(Gdx.files.internal("tic_tac_toe/cross.jpg"));
            circle = new Texture(Gdx.files.internal("tic_tac_toe/circle.png"));
        }
    }

    public Texture convertCell(String string) {
        if (string == "Cross") {
            return cross;
        } else if (string == "Circle") {
            return circle;
        }
        return null;
    }
}
