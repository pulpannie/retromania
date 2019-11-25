package com.retromania.game.colour_shooter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.colour_shooter.ColourShooterStarter;
import com.retromania.game.colour_shooter.individuals.Background;
import com.retromania.game.shared_abstractions.RetroManiaGame;

public class PlayScreen implements Screen {
    private RetroManiaGame game;
    static Stage stage;
    MainScreenInterface mainscreen;

    public OrthographicCamera camera;
    public SpriteBatch batch;

    public PlayScreen(RetroManiaGame game, MainScreenInterface mainscreen) {
        this.game = game;
        this.mainscreen = mainscreen;
        camera = new OrthographicCamera();
        batch = new SpriteBatch();
        this.stage = new Stage(new StretchViewport(Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight(), camera));
        Gdx.input.setInputProcessor(stage);

        Background background = new Background("play_screen");
        Image back_img = new Image(background.back_texture);
        back_img.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        stage.addActor(back_img);
    }

    @Override
    public void show() {
    }

    public void update(float dt) {
        stage.act(dt);
    }

    @Override
    public void render(final float delta) {
        update(delta);
        stage.draw();
        batch.begin();
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, false);
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
