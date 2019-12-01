package com.retromania.game.special_mario.views.renderables;


import java.util.Map;
import java.util.function.Supplier;


public interface UserRenderPreference {

    Map<Supplier<MarioRenderable>, String> getRenderModeFunctions();


    MarioRenderable setGameRenderNormal();

    MarioRenderable setGameRenderSurvival();

    MarioRenderable setGameRenderGhost();

    MarioRenderable getRenderable();

}
