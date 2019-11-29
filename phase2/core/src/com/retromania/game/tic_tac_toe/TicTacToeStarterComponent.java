package com.retromania.game.tic_tac_toe;


import com.retromania.game.utils.GameCamProvider;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {GameCamProvider.class})
public interface TicTacToeStarterComponent {
    TicTacToeStarter getTicTacToeStarter();
}
