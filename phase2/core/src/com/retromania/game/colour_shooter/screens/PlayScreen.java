package com.retromania.game.colour_shooter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.colour_shooter.individuals.Background;
import com.retromania.game.shared_abstractions.RetroManiaGame;

public class PlayScreen implements Screen {
    private RetroManiaGame game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Background background;

    static Stage stage;
    static ImageButton pauseButton;
    MainScreenInterface mainscreen;

    public PlayScreen(RetroManiaGame game, MainScreenInterface mainscreen) {
        this.game = game;
        this.mainscreen = mainscreen;
        gameCam = new OrthographicCamera();
        gamePort = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), gameCam);
        //stage = new Stage(gamePort, game.sb);

        // Add Pause Button
    }

    @Override
    public void show() {

    }

    public void update(float dt) {

    }

    @Override
    public void render(final float delta) {

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

    public void endGame() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
