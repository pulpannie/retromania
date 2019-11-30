package com.retromania.game.spaceship_shooter.presenter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.spaceship_shooter.individuals.Background;

public class Presenter {
    private Viewport gamePort;
    private Background background;
    private OrthographicCamera gamecam;
    public Presenter(String screenType){
        gamecam = new OrthographicCamera();
        if (screenType.equalsIgnoreCase("stretch"))
            gamePort = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), gamecam);
        else
            gamePort = new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), gamecam);
        background = new Background();
    }
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }
    public Viewport getGamePort() { return gamePort; }
    public Background getBackground(){return background;}
    public OrthographicCamera getGamecam() { return gamecam; }
}
