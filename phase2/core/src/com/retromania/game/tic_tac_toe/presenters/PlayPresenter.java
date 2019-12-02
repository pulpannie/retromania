package com.retromania.game.tic_tac_toe.presenters;

import com.badlogic.gdx.Gdx;
import com.retromania.game.shared_abstractions.User;
import com.retromania.game.tic_tac_toe.TicTacToeStarter;
import com.retromania.game.tic_tac_toe.models.TicTacToe;
import com.retromania.game.tic_tac_toe.utils.UserPreference;
import com.retromania.game.utils.GameSaver;

/**
 * presenter for PlayScreen class.
 * @author Hyokyung Kim.
 */
public class PlayPresenter{
    private UserPreference userPreference;
    private TicTacToe ticTacToe;
    private GameSaver gameSaver;
    private User currentUser;

    /**
     * creates instance of GameSaver class
     * assigns userPreference.
     */
    public PlayPresenter() {
        this.userPreference = MenuPresenter.userPreference;
        this.gameSaver = new GameSaver(TicTacToeStarter.getNameOfGame());

    }

    /**
     * @return the size of the game.
     */
    public int getSize(){
        return userPreference.getGameSize();
    }

    /**
     * @return the winner of the game.
     */
    public String getWinner(){
        return ticTacToe.getWinner();
    }

    /**
     * @param i Cell's ith row on the board.
     * @param j Cell's jth column on the board.
     * @return returns the specified Cell's state.
     */
    public String getCellState(int i, int j){
        return ticTacToe.getCellState(i, j);
    }

    /**
     * Checks if the game has ended, then handles score accordingly.
     * @returns the winner String.
     */
    public String handleEnd(){
        if (isEnd()) {
            String winner = getWinner();
            if (winner.equals("None")) {
                return "No one";
            } else if (winner.equals("Cross")) {
                currentUser = gameSaver.getCurrentUser();
                gameSaver.setScore(currentUser.getScore() + 1);
                return "Cross";

            } else if (winner.equals("Circle")) {
                return "Circle";
            }
        }
        return null;
    }

    /**
     * @return whether the game has ended.
     */
    public boolean isEnd(){
        return ticTacToe.isEnd();
    }

    /**
     * converts the coordinates where the user has touched, to the respective Cell, then touches the Cell.
     * @param tmpx x coordinate of where the user has touched.
     * @param tmpy y coordinate of where the user has touched.
     */
    public void touchCells(float tmpx, float tmpy){
        int x = (int)((tmpx * userPreference.getGameSize())/Gdx.graphics.getWidth());
        int y = (int)((tmpy * userPreference.getGameSize())/Gdx.graphics.getHeight());
        ticTacToe.touchCell(x, y);
    }

    /**
     * creates a new TicTacToe instance.
     */
    public void createTicTacToe(){
        ticTacToe = new TicTacToe(userPreference);

    }

    /**
     * @param i ith row of the Board.
     * @param j jth row of the Board.
     * @return whether the specified Cell has been touched.
     */
    public boolean isCellTouched(int i, int j){
        return ticTacToe.isCellTouched(i, j);
    }

    /**
     * @return whether the user has preferred the cat preference.
     */
    public boolean isCat() {
        return userPreference.getCat();
    }
}
