package com.retromania.game.mario.views.renderables;


import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.shared_abstractions.Renderable;
import com.retromania.game.mario.presenter.MarioGamePresentable;


public interface MarioShowable extends Renderable {
    Viewport getGamePort();
    void resize(int width, int height);
    /**
     *
     * Checks whether or not we need to change the tile map, does it and returns the true value if
     * there is need to do that.
     *
     * **/
    boolean checkTiledMap();

    void start();
    void dispose();

    void setPresenter(MarioGamePresentable marioGamePresentable);

    void reloadLevel();


}
