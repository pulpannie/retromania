package com.retromania.game.tic_tac_toe;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.shared_abstractions.RetroManiaInnerGame;

import java.util.List;

public class TicTacToeStarter extends RetroManiaInnerGame {
    Stage stage;
    public TicTacToeStarter(RetroManiaGame game) {
        super(game, "Tic Tac Toe Game", RetroManiaGame.Orientation.VERTICAL);
    }

    @Override
    public void save(Object... args) {

    }

    @Override
    public List<Object> retreave() {
        return null;
    }

    @Override
    public void handleInput() {

    }

    @Override
    public void show() {

        stage = new Stage();
        stage.addActor(new Label("The RetroMania", new Label.LabelStyle(new BitmapFont(), Color.RED)));

    }

    @Override
    public void render(float delta) {

        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        game.sb.setProjectionMatrix(stage.getCamera().combined);
        stage.draw();
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
