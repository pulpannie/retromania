package com.retromania.game.shared_abstractions;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.RetroMania;

import java.util.ArrayList;
import java.util.List;

public abstract class RetroManiaScreen implements Screen {
    public abstract void handleInput();

    public Viewport gamePort;
    public RetroManiaGame game;

    public List<Screen> innerScreens = new ArrayList<>();

    Texture texture;


    public RetroManiaScreen(RetroManiaGame game){
        this.game = game;
    }
    public RetroManiaScreen(){game = RetroMania.getRetroManiaInstance();}

    public void update(){
        handleInput();
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}

}
