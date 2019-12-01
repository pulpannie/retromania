package com.retromania.game.special_mario.views.renderables;


import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.shared_abstractions.Renderable;

public interface MarioRenderable extends Renderable {
    Viewport getGamePort();
    void resize(int width, int height);
    boolean checkTiledMap();
    void resetWorldRenderTiles();
}
