package com.retromania.game.special_mario.views.renderables;

import javax.inject.Inject;

public class UserRenderPreferenceMario implements UserRenderPreference {

    private GameRenderer gameRenderer;
    private MarioRenderable selectedRenderer;
    @Inject
    UserRenderPreferenceMario(GameRenderer gameRenderer){
        this.gameRenderer = gameRenderer;
        setGameRenderNormal();
    }

    @Override
    public void setGameRenderNormal() {
        this.selectedRenderer = gameRenderer;
    }

    @Override
    public void setGameRenderSurvival() {
        throw new RuntimeException("this renderer has not been implemented yet.");
    }

    @Override
    public MarioRenderable getRenderable() {
        System.out.println("Hey look a me "+ selectedRenderer);
        return selectedRenderer;
    }
}
