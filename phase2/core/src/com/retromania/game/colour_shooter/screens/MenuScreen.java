package com.retromania.game.colour_shooter.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.colour_shooter.individuals.Background;
import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.badlogic.gdx.scenes.scene2d.Stage;


public class MenuScreen implements Screen {
    private RetroManiaGame game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Background background;

    public Stage stage;

    MainScreenInterface mainscreen;

    public MenuScreen(RetroManiaGame game, MainScreenInterface mainscreen) {
        this.game = game;
        this.mainscreen = mainscreen;
        gameCam = new OrthographicCamera();
        //stage = new Stage(gamePort, game.sb);
        background = new Background();

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
