package com.retromania.game.special_mario.views.renderables;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.RetroMania;
import com.retromania.game.shared_abstractions.Presentable;
import com.retromania.game.shared_abstractions.Renderable;
import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.special_mario.SpecialMarioConfiguration;
import com.retromania.game.special_mario.models.player.MainPlayer;
import com.retromania.game.special_mario.presenter.MarioGamePresentable;
import com.retromania.game.special_mario.presenter.SpecialMarioStarterPresenter;

public abstract class MarioRenderable implements Renderable, MarioShowable{

    MarioGamePresentable marioGamePresentable;
    TiledMap currTiledMap;


    OrthogonalTiledMapRenderer orthogRenderer;
    RetroManiaGame game = RetroMania.getRetroManiaInstance();
    Box2DDebugRenderer b2ddr;
    OrthographicCamera gamecam;

    Viewport gamePort;
    MainPlayer mainPlayer;
    World world;


    MarioRenderable(MainPlayer mainPlayer,
                    World world, SpecialMarioStarterPresenter marioGamePresenter){
        this.marioGamePresentable = marioGamePresenter;
        this.currTiledMap = this.marioGamePresentable.getTileMap();
        this.world = world;
        this.mainPlayer = mainPlayer;
        setUpGamecam();
        setUpGamePort();
        setUpOrthogRenderer();
        b2ddr = new Box2DDebugRenderer();
    }

    private void setUpGamecam() {
        gamecam = new OrthographicCamera();
    }

    private void setUpGamePort() {
        gamePort =
                new FitViewport(
                        SpecialMarioConfiguration.convertPixelToMeter(RetroManiaGame.V_WIDTH),
                        SpecialMarioConfiguration.convertPixelToMeter(RetroManiaGame.V_HEIGHT),
                        gamecam);
        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
    }


    private void setUpOrthogRenderer() {
        orthogRenderer =
                new OrthogonalTiledMapRenderer(
                        marioGamePresentable.getTileMap(),
                        SpecialMarioConfiguration.getPixelToMeterConversionRate());
    }

    @Override
    public boolean checkTiledMap() {
        if (currTiledMap != this.marioGamePresentable.getTileMap()){
            setUpOrthogRenderer();
            return true;
        }
        return false;
    }

    void clearDisplay() {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    public Viewport getGamePort() {
        return gamePort;
    }

    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

}
