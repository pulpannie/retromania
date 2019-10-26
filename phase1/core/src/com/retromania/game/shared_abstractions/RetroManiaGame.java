package com.retromania.game.shared_abstractions;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.retromania.game.GameLister;

public abstract class RetroManiaGame extends Game {
    public static final int V_WIDTH = 400; //virtual width of game
    public static final int V_HEIGHT = 208; //virtual height of game;


    public SpriteBatch batch;
    protected Texture img;
    protected BitmapFont font;


    @Override
    public void create () {
        batch = new SpriteBatch();
        setScreen(new GameLister(this)); //set the current gamescreen through this method.
    }
}
