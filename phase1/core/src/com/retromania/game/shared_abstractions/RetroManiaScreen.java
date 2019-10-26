package com.retromania.game.shared_abstractions;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.Retromania;

public abstract class RetroManiaScreen implements Screen {
    public abstract void handleInput();

    public OrthographicCamera gamecam;
    public Viewport gamePort;
    public RetroManiaGame game;
    Texture texture;


    public RetroManiaScreen(RetroManiaGame game){
        gamecam = new OrthographicCamera();
        gamecam.setToOrtho(false, RetroManiaGame.V_WIDTH, RetroManiaGame.V_HEIGHT);
        gamePort = new FitViewport(Retromania.V_WIDTH, Retromania.V_HEIGHT, gamecam);
        this.game = game;
    }

    void update(){
        handleInput();
    }
}
