package com.retromania.game.spaceship_shooter.Views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.retromania.game.RetroMania;
import com.retromania.game.shared_abstractions.RetroManiaScreen;
import com.retromania.game.spaceship_shooter.Models.ImageButtonBuilder;

import com.retromania.game.spaceship_shooter.Presenters.MenuScreenPresenter;

public class MenuScreen extends RetroManiaScreen {
    public Stage stage;
    private ImageButton startButton;
    private MenuScreenPresenter presenter;


    public MenuScreen(MainScreenInterface mainScreen){
        presenter = new MenuScreenPresenter("stretch", mainScreen);
        stage = new Stage(presenter.getGamePort(), RetroMania.getRetroManiaInstance().sb);
        startButton = (new ImageButtonBuilder()).buildTexture("start_button.png").buildButton();
        startButton.setPosition(Gdx.graphics.getWidth()/2-400, Gdx.graphics.getHeight()/2 - 600);
        startButton.setSize(800, 800);
        stage.addActor(startButton);
        Gdx.input.setInputProcessor(stage);

    }
    @Override
    public void show() {
        stage.addActor(startButton);
        presenter.updateTable();

        Gdx.input.setInputProcessor(stage);
    }

    public void handleInput(){
        if (startButton.isPressed())
            start();
    }

    public void update(float dt){
        handleInput();

        stage.act();
        presenter.update(dt);
    }

    @Override
    public void render(final float delta) {
        update(delta);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        RetroMania.getRetroManiaInstance().sb.begin();
        presenter.getBackground().draw(RetroMania.getRetroManiaInstance().sb, delta);
        RetroMania.getRetroManiaInstance().sb.end();
        stage.draw();
        presenter.getStage().draw();
    }

    @Override
    public void resize(int width, int height) { presenter.resize(width, height); }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
    }

    private void start(){
        dispose();
        presenter.start();
    }
    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}

