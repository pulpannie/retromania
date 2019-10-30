package com.retromania.game.tic_tac_toe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.shared_abstractions.RetroManiaScreen;

public class GameOverScreen extends RetroManiaScreen {
    public SpriteBatch batch;
    public float gameWidth, gameHeight;
    public Stage stage;
    BitmapFont font = new BitmapFont();
    String winner;
    public OrthographicCamera gamecam;



    public GameOverScreen(RetroManiaGame game, String winner) {
        super(game);
        this.winner = winner;
    }

    @Override
    public void show() {
        gameWidth = Gdx.graphics.getWidth();
        gameHeight = Gdx.graphics.getHeight();
        stage = new Stage(new FitViewport(gameWidth, gameHeight, gamecam));
        Gdx.input.setInputProcessor(stage);
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(delta);
        stage.draw();
        batch.begin();
        font.getData().setScale(5, 5);
        font.draw(batch, this.winner + "wins!", gameWidth/2, gameHeight/2);
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
