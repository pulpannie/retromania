package com.retromania.game.spaceship_shooter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.retromania.game.RetroMania;
import com.retromania.game.shared_abstractions.RetroManiaScreen;
import com.retromania.game.spaceship_shooter.individuals.ImageButtonBuilder;
import com.retromania.game.spaceship_shooter.utils.PauseScreenRenderer;


public class PauseScreen extends RetroManiaScreen {
    private ImageButton resumeButton;
    private ImageButton restartButton;
    private ImageButton settingButton;
    private Stage stage;
    MainScreenInterface mainscreen;
    private PauseScreenRenderer renderer;
    public PauseScreen(MainScreenInterface mainscreen){
        renderer = new PauseScreenRenderer("fill");
        this.mainscreen = mainscreen;
        stage = new Stage(renderer.getGamePort(), RetroMania.getRetroManiaInstance().sb);

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

        renderer.update(dt);
        stage.act();
    }

    @Override
    public void render(final float delta) {
        update(delta);
        renderer.render(delta);
        stage.draw();


    }

    @Override
    public void resize(int width, int height) {
        renderer.resize(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
        stage.dispose();
        mainscreen.resume();
    }

    public void restart(){
        stage.dispose();
        mainscreen.restart();
    }

    public void modify(){
        stage.dispose();
        mainscreen.modify();
    }
    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}


