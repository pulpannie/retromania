package com.retromania.game.tic_tac_toe;

import com.badlogic.gdx.Gdx;

import static com.retromania.game.tic_tac_toe.TicTacToeStarter.NAME_OF_GAME;

public class TicTacToeConfiguration {
  public static final TicTacToeStarter FINAL_GAME;

  static {
    // TODO Fix the passing of hight and width
    FINAL_GAME =
        DaggerTicTacToeStarterComponent.builder()
            .setGameWidth(Gdx.graphics.getHeight())
            .setGameHeight(Gdx.graphics.getWidth())
            .setGameName(NAME_OF_GAME)
            .build()
            .getTicTacToeStarter();
  }
}
