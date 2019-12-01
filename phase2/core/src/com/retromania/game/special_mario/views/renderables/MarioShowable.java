package com.retromania.game.special_mario.views.renderables;


import com.badlogic.gdx.utils.viewport.Viewport;

public interface MarioShowable{
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
}
