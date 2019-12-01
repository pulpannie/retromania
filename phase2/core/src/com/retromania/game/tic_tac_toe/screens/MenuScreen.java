package com.retromania.game.tic_tac_toe.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.RetroMania;
import com.retromania.game.shared_abstractions.RetroManiaScreen;
import com.retromania.game.tic_tac_toe.presenters.MenuPresenter;
import com.retromania.game.tic_tac_toe.utils.ImageButtonBuilder;

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
    private ImageButton catButton, upButton, downButton;
    private Viewport viewport;
    private PlayScreen playScreen;
    private MenuPresenter menuPresenter;
    private ImageButtonBuilder imageButtonBuilder;
    private Texture title;

    @Inject
    public MenuScreen(OrthographicCamera gamecam, PlayScreen playScreen) {
        this.menuPresenter = new MenuPresenter(playScreen);
        this.playScreen = playScreen;
        this.gamecam = gamecam;
        this.imageButtonBuilder = new ImageButtonBuilder();
    }

    @Override
    public void show() {

        gameWidth = Gdx.graphics.getWidth();
        gameHeight = Gdx.graphics.getHeight();
        viewport = new FillViewport(gameWidth, gameHeight, gamecam);

        batch = new SpriteBatch();

        buildButtons();
        configureButtons();

        title = new Texture(Gdx.files.internal("tic_tac_toe/title.png"));

        stage = new Stage(viewport, game.sb);
        stage.addActor(catButton);
        stage.addActor(playButton);
        stage.addActor(upButton);
        stage.addActor(downButton);
        Gdx.input.setInputProcessor(stage);


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

    private void buildButtons() {
        buildPlayButton();
        buildCatButton();
        buildSizeButtons();
    }

    private void configureButtons(){
        playButton.setSize(250, 250);
        playButton.setPosition(Gdx.graphics.getWidth() / 2 - 130, Gdx.graphics.getHeight() / 3 + 30);
        catButton.setSize(60, 60);
        catButton.setPosition(Gdx.graphics.getWidth() / 2 - 120, Gdx.graphics.getHeight() / 4 - 50);
        upButton.setSize(50, 50);
        upButton.setPosition(Gdx.graphics.getWidth() / 2 + 50, Gdx.graphics.getHeight() / 4);
        downButton.setSize(50, 50);
        downButton.setPosition(Gdx.graphics.getWidth() / 2 + 50, Gdx.graphics.getHeight() / 4 - 100);
    }

    private void buildPlayButton() {
        playButton = imageButtonBuilder.buildButton("play_tictactoe.png");
    }

    /**
     * Create cat buttons, tutorial from "https://alvinalexander.com/source-code/how-create-libgdx-scene2d-imagebutton"
     */
    private void buildCatButton() {
        catButton = imageButtonBuilder.buildOnOffButton("radio-off-button.png", "radio-on-button.png");
        catButton.addListener(new ClickListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                menuPresenter.reverseCat();
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                return true;
            }
        });
    }

    private void buildSizeButtons() {
        upButton = imageButtonBuilder.buildButton("up.png");
        upButton.addListener(new ClickListener() {
                                 public void clicked(InputEvent event, float x, float y) {
                                     menuPresenter.addSize();
                                 }
                             }
        );

        downButton = imageButtonBuilder.buildButton("down.png");
        downButton.addListener(new ClickListener() {
                                   public void clicked(InputEvent event, float x, float y) {
                                       if (menuPresenter.getGameSize() > 3) {
                                           menuPresenter.decreaseSize();
                                       }
                                   }
                               }
        );
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    public void dispose() {
        stage.dispose();
    }
}
