package com.retromania.game.spaceship_shooter.Presenters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.spaceship_shooter.SpaceShipShooterStarter;
import com.retromania.game.spaceship_shooter.Models.Background;

public class Presenter {
    private Viewport gamePort;
    private Background background;
    private OrthographicCamera gameCam;
    public Presenter(String screenType){
        gameCam = new OrthographicCamera();
        if (screenType.equalsIgnoreCase("stretch"))
            gamePort = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), gameCam);
        else
            gamePort = new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), gameCam);
        background = new Background();
    }
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }
    public Viewport getGamePort() { return gamePort; }
    public Background getBackground(){return background;}

    public void update(float dt){
        gameCam.update();

        SpaceShipShooterStarter.playMusic();
    }
}
