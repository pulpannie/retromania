package com.retromania.game.spaceship_shooter.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.RetroMania;
import com.retromania.game.shared_abstractions.Renderable;
import com.retromania.game.spaceship_shooter.individuals.Background;

public class GameRenderer implements Renderable {
    private OrthographicCamera gamecam;

    public Viewport getGamePort() {
        return gamePort;
    }

    private Viewport gamePort;
    private Background background;

    public GameRenderer(String screenType){
        gamecam = new OrthographicCamera();
        if (screenType.equalsIgnoreCase("stretch"))
            gamePort = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), gamecam);
        else
            gamePort = new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), gamecam);
        background = new Background();
    }

    public void handleInput(){
    }

    public void update(float dt){
        handleInput();

        gamecam.update();
    }

    @Override
    public void render(float delta) {
        update(delta);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        RetroMania.getRetroManiaInstance().sb.begin();
        background.draw(RetroMania.getRetroManiaInstance().sb, delta);

        RetroMania.getRetroManiaInstance().sb.end();
    }

    public void resize(int width, int height) {
        gamePort.update(width, height);
    }
}
