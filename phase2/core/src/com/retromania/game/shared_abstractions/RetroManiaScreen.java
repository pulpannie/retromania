package com.retromania.game.shared_abstractions;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.RetroMania;
import com.sun.org.apache.xpath.internal.operations.Mod;

import java.util.ArrayList;
import java.util.List;

public abstract class RetroManiaScreen implements Screen {
    public abstract void handleInput();

    protected Viewport gamePort;
    public RetroManiaGame game;



    private List<Renderable> renderables = new ArrayList<>();
    private List<RetroManiaModel> models = new ArrayList<>();

    public List<Screen> innerScreens = new ArrayList<>();

    Texture texture;


    public RetroManiaScreen(RetroManiaGame game){
        this.game = game;
    }
    public RetroManiaScreen(){game = RetroMania.getRetroManiaInstance();}


    public void update(){
        handleInput();
        for(RetroManiaModel model : models){
            model.update();
        }
    }

    @Override
    public void render(float delta){
        update();
        for (Renderable renderable : renderables){
            renderable.render(delta);
        }
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}

    public void addRenderable(Renderable renderable) {
        this.renderables.add(renderable);
    }
    public void addModel(RetroManiaModel model) {
        this.models.add(model);
    }

}
