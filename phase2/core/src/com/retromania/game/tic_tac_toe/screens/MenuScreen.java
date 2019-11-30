package com.retromania.game.tic_tac_toe.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.viewport.FillViewport;

import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.shared_abstractions.RetroManiaScreen;

import com.retromania.game.tic_tac_toe.individuals.ImageButtonBuilder;
import com.retromania.game.tic_tac_toe.presenters.MenuPresenter;
import com.retromania.game.tic_tac_toe.utils.UserPrefrence;
import com.retromania.game.utils.GameSaver;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MenuScreen extends RetroManiaScreen {
    public Stage stage;
    public SpriteBatch batch;
    public OrthographicCamera gamecam;
    BitmapFont font = new BitmapFont();
    public float gameWidth;
    public float gameHeight;
    ImageButton playButton;
    ImageButton catButton, upButton, downButton;
    Viewport viewport;
    PlayScreen playScreen;
    MenuPresenter menuPresenter;
//    GameSaver gameSaver;
    @Inject
    public MenuScreen(OrthographicCamera gamecam, UserPrefrence userPrefrence, PlayScreen playScreen) {
//        this.gameSaver = new GameSaver();
        this.menuPresenter = new MenuPresenter("fill",gamecam, userPrefrence, playScreen);
        this.playScreen = playScreen;
        this.gamecam = gamecam;
    }

    @Override
    public void handleInput() {
        if (playButton.isPressed()) {
            game.setScreen(playScreen);
        }
    }

    @Override
    public void show() {


        gameWidth = Gdx.graphics.getWidth();
        gameHeight = Gdx.graphics.getHeight();
        viewport = new FillViewport(gameWidth, gameHeight, gamecam);
        stage = new Stage(viewport, game.sb);
        Gdx.input.setInputProcessor(stage);
        batch = new SpriteBatch();

        menuPresenter.buildButtons();

        playButton = menuPresenter.getPlayButton();
        catButton = menuPresenter.getCatButton();
        upButton = menuPresenter.getUpButton();
        downButton = menuPresenter.getDownButton();

        stage.addActor(playButton);
        stage.addActor(catButton);
        stage.addActor(upButton);
        stage.addActor(downButton);;


        Gdx.input.setInputProcessor(stage);

    }

    @Override
    public void render(float delta) {
        handleInput();
        Gdx.gl.glClearColor(1, 1, 1, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        game.sb.setProjectionMatrix(stage.getCamera().combined);
        stage.draw();
        batch.setProjectionMatrix(gamecam.combined);
        batch.begin();
        font.setColor(Color.BLACK);
        font.getData().setScale(2, 2);
        font.draw(batch, "CATS!", gameWidth / 2 - 210, gameHeight / 3 - 30);
        batch.end();
        batch.begin();
        font.draw(batch, Integer.toString(menuPresenter.getUserPrefrence().getGameSize()), gameWidth / 2 - 50, gameHeight / 3 + 30);
        batch.end();

    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    public void dispose() {
        stage.dispose();
    }
}
