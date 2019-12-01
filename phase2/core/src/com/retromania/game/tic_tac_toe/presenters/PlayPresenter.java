package com.retromania.game.tic_tac_toe.presenters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.retromania.game.RetroMania;
import com.retromania.game.shared_abstractions.User;
import com.retromania.game.tic_tac_toe.TicTacToeStarter;
import com.retromania.game.tic_tac_toe.individuals.Cell;
import com.retromania.game.tic_tac_toe.individuals.CellManager;
import com.retromania.game.tic_tac_toe.individuals.TicTacToe;
import com.retromania.game.tic_tac_toe.screens.GameOverScreen;
import com.retromania.game.tic_tac_toe.utils.UserPreference;
import com.retromania.game.utils.GameSaver;

public class PlayPresenter{
    private Texture cross, circle;
    private UserPreference userPreference;
    private TicTacToe ticTacToe;
    private CellManager cellManager;
    private GameSaver gameSaver;
    private User currentUser;

    public PlayPresenter() {
        this.userPreference = MenuPresenter.userPreference;
        this.gameSaver = new GameSaver(TicTacToeStarter.getNameOfGame());

    }

    public int getSize(){
        return userPreference.getGameSize();
    }

    public String getWinner(){
        return ticTacToe.getWinner();
    }

    public Cell[][] getCells(){
        return ticTacToe.getCellStates();
    }

    public String getCellState(int i, int j){
        return ticTacToe.getCellState(i, j);
    }

    public void checkEnd(){
        if (isEnd()) {
            String winner = getWinner();
            if (winner.equals("None")) {
                RetroMania.getRetroManiaInstance().setScreen(new GameOverScreen(RetroMania.getRetroManiaInstance(), "No one"));
            } else if (winner.equals("Cross")) {
                                currentUser = gameSaver.getCurrentUser();
                                gameSaver.setScore(currentUser.getScore() + 1);
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
        int x = (int)((tmpx * userPreference.getGameSize())/Gdx.graphics.getWidth());
        int y = (int)((tmpy * userPreference.getGameSize())/Gdx.graphics.getHeight());
        ticTacToe.touchCell(x, y);
        System.out.println("XY" + Integer.toString(x) + Integer.toString(y));
    }


    public void createTicTacToe(){
        ticTacToe = new TicTacToe(userPreference);

    }

    public boolean isCellTouched(int i, int j){
        return ticTacToe.isCellTouched(i, j);
    }

    public void setTextures(){
        if (userPreference.getCat()) {
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
