package com.retromania.game.tic_tac_toe;


import com.retromania.game.utils.GameCamProvider;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;


@Singleton
@Component(modules = {GameCamProvider.class})
public interface TicTacToeStarterComponent {
    TicTacToeStarter getTicTacToeStarter();

    @Component.Builder
    interface Builder{

        @BindsInstance
        Builder setGameWidth(@Named("Game Width") int gameWidth);

        @BindsInstance
        Builder setGameHeight(@Named("Game Height") int gameHeight);

        TicTacToeStarterComponent build();
    }
}
