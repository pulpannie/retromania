package com.retromania.game.spaceship_shooter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.retromania.game.RetroMania;
import com.retromania.game.shared_abstractions.RetroManiaScreen;
import com.retromania.game.spaceship_shooter.individuals.ImageButtonBuilder;
import com.retromania.game.spaceship_shooter.presenters.PauseScreenPresenter;


public class PauseScreen extends RetroManiaScreen {
    private ImageButton resumeButton;
    private ImageButton restartButton;
    private ImageButton settingButton;
    private Stage stage;
    private PauseScreenPresenter presenter;

    public PauseScreen(MainScreenInterface mainScreen){
        presenter = new PauseScreenPresenter("fill", mainScreen);
        stage = new Stage(presenter.getGamePort(), RetroMania.getRetroManiaInstance().sb);

        resumeButton = (new ImageButtonBuilder()).buildTexture("resume.png").buildButton();
        resumeButton.setPosition(Gdx.graphics.getWidth()/2-150, Gdx.graphics.getHeight()/2 + 100);
        resumeButton.setSize(300, 300);


        restartButton = (new ImageButtonBuilder()).buildTexture("restart.png").buildButton();
        restartButton.setPosition(Gdx.graphics.getWidth()/2-110, Gdx.graphics.getHeight()/2-50);
        restartButton.setSize(200, 200);

        settingButton = (new ImageButtonBuilder()).buildTexture("setting.png").buildButton();
        settingButton.setPosition(Gdx.graphics.getWidth()/2-110, Gdx.graphics.getHeight()/2 - 300);
        settingButton.setSize(200, 200);

        Gdx.input.setInputProcessor(stage);

    }
    @Override
    public void show() {
        stage.addActor(resumeButton);
        stage.addActor(restartButton);
        stage.addActor(settingButton);
        Gdx.input.setInputProcessor(stage);
    }

    public void handleInput(){
        if (resumeButton.isPressed()) {
            resume();
        }
        else if(restartButton.isPressed()){
            restart();
        }

        else if(settingButton.isPressed()) {
            modify();
        }
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
    }

    @Override
    public void resize(int width, int height) {
        presenter.resize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
        dispose();
        presenter.resume();
    }

    private void restart(){
        dispose();
        presenter.restart();
    }

    private void modify(){
        dispose();
        presenter.modify();
    }
    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}


