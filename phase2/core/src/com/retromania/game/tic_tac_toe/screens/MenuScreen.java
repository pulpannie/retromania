package com.retromania.game.tic_tac_toe.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.RetroMania;
import com.retromania.game.shared_abstractions.RetroManiaScreen;
import com.retromania.game.tic_tac_toe.presenters.MenuPresenter;
import com.retromania.game.tic_tac_toe.utils.MenuScreenButtonHelper;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MenuScreen extends RetroManiaScreen {
    private Stage stage;
    private SpriteBatch batch;
    private OrthographicCamera gamecam;
    private BitmapFont font = new BitmapFont();
    private float gameWidth;
    private float gameHeight;
    private ImageButton playButton;
    private Viewport viewport;
    private PlayScreen playScreen;
    private MenuPresenter menuPresenter;
    private Texture title;
    private MenuScreenButtonHelper menuScreenButtonHelper;
    private ArrayList<ImageButton> imageButtons;

    @Inject
    public MenuScreen(OrthographicCamera gamecam, PlayScreen playScreen) {
        this.menuPresenter = new MenuPresenter(playScreen);
        this.playScreen = playScreen;
        this.gamecam = gamecam;
        this.menuScreenButtonHelper = new MenuScreenButtonHelper(menuPresenter);
    }

    @Override
    public void show() {

        gameWidth = Gdx.graphics.getWidth();
        gameHeight = Gdx.graphics.getHeight();
        viewport = new FillViewport(gameWidth, gameHeight, gamecam);
        batch = new SpriteBatch();
        title = new Texture(Gdx.files.internal("tic_tac_toe/title.png"));
        stage = new Stage(viewport, game.sb);

        imageButtons = menuScreenButtonHelper.makeButtons();
        for (int i = 0; i < imageButtons.size(); i++){
            stage.addActor(imageButtons.get(i));
        }

        Gdx.input.setInputProcessor(stage);

        playButton = menuScreenButtonHelper.getPlayButton();

    }

    public void handleInput() {
        if (playButton.isPressed()) {
            RetroMania.getRetroManiaInstance().setScreen(playScreen);
        }
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
        drawTitle();
        writeCats();
        writeSize();

    }

    private void drawTitle(){
        batch.begin();
        batch.draw(
                title,
                gameWidth*2/6+40,
                gameHeight*2/3, 500, 150);
        batch.end();
    }

    private void writeCats(){
        batch.begin();
        font.setColor(Color.BLACK);
        font.getData().setScale(2, 2);
        font.draw(batch, "CATS!", gameWidth / 2 - 120, gameHeight / 4- 70);
        batch.end();
    }

    private void writeSize(){
        batch.begin();
        font.draw(batch, Integer.toString(menuPresenter.getGameSize()), gameWidth / 2 + 65, gameHeight / 4 - 20);
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
