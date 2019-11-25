package com.retromania.game.colour_shooter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.colour_shooter.ColourShooterStarter;
import com.retromania.game.colour_shooter.individuals.Background;
import com.retromania.game.shared_abstractions.RetroManiaGame;


public class PauseScreen implements Screen {

    private RetroManiaGame game;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private Background background;
    private ImageButton exitButton;
    private ImageButton resumeButton;
    private ImageButton restartButton;

    public Stage stage;
    MainScreenInterface mainscreen;

    public PauseScreen (RetroManiaGame game, MainScreenInterface mainscreen) {
        this.game = game;
        gameCam = new OrthographicCamera();
        gamePort = new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), gameCam);
        this.mainscreen = mainscreen;
        //stage = new Stage(gamePort, game.sb);
        background = new Background("pause_screen");

        // Add the buttons

        //Gdx.input.setInputProcessor(stage);


    }


    @Override
    public void show() {

    }

    public void handleInput(float dt) {
        // What happens when button is pressed
    }

    public void update(float dt){

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
