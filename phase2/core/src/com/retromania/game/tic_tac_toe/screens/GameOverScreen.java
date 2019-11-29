package com.retromania.game.tic_tac_toe.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.retromania.game.GameLister;
import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.shared_abstractions.RetroManiaScreen;

public class GameOverScreen extends RetroManiaScreen {
    public SpriteBatch batch;
    public float gameWidth, gameHeight;
    public Stage stage;
    BitmapFont font = new BitmapFont();
    String winner;
    public OrthographicCamera gamecam;
//    Texture replayTexture;
//    ImageButton replayButton;
//    Texture menuTexture;
//    ImageButton menuButton;


    public GameOverScreen(RetroManiaGame game, String winner) {
        super(game);
        this.winner = winner;
    }

    @Override
    public void show() {
        gamecam = new OrthographicCamera();
        gamecam.setToOrtho(false, gameWidth, gameHeight);
        gameWidth = Gdx.graphics.getWidth();
        gameHeight = Gdx.graphics.getHeight();

        stage = new Stage(new FitViewport(gameWidth, gameHeight, gamecam));
        Gdx.input.setInputProcessor(stage);
        batch = new SpriteBatch();

//        replayTexture= new Texture(Gdx.files.internal("replay_tictactoe.png"));
//        TextureRegion replayTextureRegion = new TextureRegion(replayTexture);
//        TextureRegionDrawable replayTextureRegionDrawable =
//                new TextureRegionDrawable(replayTextureRegion);
//        replayButton = new ImageButton(replayTextureRegionDrawable);
//        replayButton.setSize(400, 400);
//        replayButton.setPosition(200, 500);
//        stage.addActor(replayButton);
//        menuTexture= new Texture(Gdx.files.internal("menu_tictactoe.png"));
//        TextureRegion menuTextureRegion = new TextureRegion(menuTexture);
//        TextureRegionDrawable menuTextureRegionDrawable =
//                new TextureRegionDrawable(menuTextureRegion);
//        menuButton = new ImageButton(menuTextureRegionDrawable);
//        menuButton.setSize(200, 200);
//        menuButton.setPosition(600, 200);
//        stage.addActor(menuButton);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
        batch.begin();
        font.getData().setScale(5, 5);
        font.draw(batch, this.winner + "wins!", gameWidth / 2, gameHeight / 2);
        batch.end();

    }

    @Override
    public void handleInput() {

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
