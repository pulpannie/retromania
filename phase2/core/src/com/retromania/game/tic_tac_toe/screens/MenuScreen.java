package com.retromania.game.tic_tac_toe.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.shared_abstractions.RetroManiaScreen;
import com.retromania.game.tic_tac_toe.individuals.ImageButtonBuilder;
import com.retromania.game.utils.GameSaver;

public class MenuScreen extends RetroManiaScreen {
    public Stage stage;
    public SpriteBatch batch;
    public OrthographicCamera gamecam;
    BitmapFont font = new BitmapFont();
    public float gameWidth;
    public float gameHeight;
    ImageButtonBuilder imageButtonBuilder;
    ImageButton playButton;
    ImageButton catButton, upButton, downButton;
    Viewport viewport;
    GameSaver gameSaver;
    private int size = 3;

    public MenuScreen(GameSaver gameSaver) {
        this.gameSaver = gameSaver;
        gamecam = new OrthographicCamera();
        gameWidth = Gdx.graphics.getWidth();
        gameHeight = Gdx.graphics.getHeight();
        viewport = new FillViewport(gameWidth, gameHeight, gamecam);
        stage = new Stage(viewport, game.sb);
        Gdx.input.setInputProcessor(stage);
        batch = new SpriteBatch();
        Table table = new Table();
        table.setBounds(0, 0, gameWidth, gameHeight);
//        uiSkin = new Skin(Gdx.files.internal("tic_tac_toe/uiskin.json"));
        imageButtonBuilder = new ImageButtonBuilder();
        playButton = imageButtonBuilder.buildButton(new Texture(Gdx.files.internal("tic_tac_toe/play_tictactoe.png")), 200, 200, gameWidth / 2 - 110, gameHeight / 2 - 70);
        stage.addActor(playButton);

        /**Create cat buttons, tutorial from "https://alvinalexander.com/source-code/how-create-libgdx-scene2d-imagebutton" */
        Texture catTexture = new Texture(Gdx.files.internal("tic_tac_toe/radio-off-button.png"));
        Texture catTexturePressed = new Texture(Gdx.files.internal("tic_tac_toe/radio-on-button.png"));
        catButton = imageButtonBuilder.buildButton(catTexture, catTexturePressed, 60, 60, gameWidth / 2 - 220, gameHeight / 3);
        stage.addActor(catButton);

        upButton = imageButtonBuilder.buildButton(new Texture(Gdx.files.internal("tic_tac_toe/up.png")), 50, 50, gameWidth / 2 - 50, gameHeight / 3 + 40);
        upButton.addListener(new ClickListener() {
                                 public void clicked(InputEvent event, float x, float y) {
                                     size++;
                                 }
                             }
        );
        stage.addActor(upButton);

        downButton = imageButtonBuilder.buildButton(new Texture(Gdx.files.internal("tic_tac_toe/down.png")), 50, 50, gameWidth / 2 - 50, gameHeight / 3 - 40);
        downButton.addListener(new ClickListener() {
                                   public void clicked(InputEvent event, float x, float y) {
                                       if (size > 3) {
                                           size--;
                                       }
                                   }
                               }
        );
        stage.addActor(downButton);

        stage.addActor(table);
        table.debug();

    }

    @Override
    public void handleInput() {
        if (playButton.isPressed()) {
            game.setScreen(new PlayScreen(catButton.isChecked(), size, gameSaver));
        }
    }

    @Override
    public void show() {
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
        font.draw(batch, Integer.toString(size), gameWidth / 2 - 50, gameHeight / 3 + 30);
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
