package com.retromania.game.special_mario.views.renderables;


import javax.inject.Inject;
import javax.inject.Named;

import dagger.Module;
import dagger.Provides;


@Module
public class RendererProvider {

    public static final String  GAME_RENDERER = "game renderer";
    @Provides
    @Inject
    @Named(GAME_RENDERER)
    MarioRenderable getRenderer(GameRenderer gameRenderer){
        return gameRenderer;
    }
}
