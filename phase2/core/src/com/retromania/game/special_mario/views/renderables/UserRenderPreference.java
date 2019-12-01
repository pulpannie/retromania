package com.retromania.game.special_mario.views.renderables;


import java.util.Map;
import java.util.function.Supplier;


public interface UserRenderPreference {

    Map<Supplier<MarioShowable>, String> getRenderModeFunctions();


    MarioShowable setGameRenderNormal();

    MarioShowable setGameRenderSurvival();

    MarioShowable setGameRenderGhost();

    MarioShowable getRenderable();

    void start();

}
