package com.retromania.game.spaceship_shooter.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.RetroMania;
import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.shared_abstractions.RetroManiaScreen;
import com.retromania.game.spaceship_shooter.SpaceShipShooterStarter;
import com.retromania.game.spaceship_shooter.individuals.Background;
import com.retromania.game.spaceship_shooter.individuals.ImageButtonBuilder;
import com.retromania.game.spaceship_shooter.utils.GameRenderer;


public class PauseScreen extends RetroManiaScreen {
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private ImageButton resumeButton;
    private ImageButton restartButton;
    private ImageButton settingButton;
    private Background background;
    private Stage stage;
    MainScreenInterface mainscreen;
    private GameRenderer renderer;
    public PauseScreen(MainScreenInterface mainscreen){
        renderer = new GameRenderer("fill");
//        gamecam = new OrthographicCamera();
//        gamePort = new FillViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), gamecam);
//        background = new Background();
        this.mainscreen = mainscreen;
        stage = new Stage(renderer.getGamePort(), RetroMania.getRetroManiaInstance().sb);

        resumeButton = (new ImageButtonBuilder()).buildTexture("resume.png").buildButton();
        resumeButton.setPosition(Gdx.graphics.getWidth()/2-150, Gdx.graphics.getHeight()/2 + 100);
        resumeButton.setSize(300, 300);
        resumeButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                resume();
            }
        });
        stage.addActor(resumeButton);


        restartButton = (new ImageButtonBuilder()).buildTexture("restart.png").buildButton();
        restartButton.setPosition(Gdx.graphics.getWidth()/2-110, Gdx.graphics.getHeight()/2-50);
        restartButton.setSize(200, 200);
        restartButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                restart();
            }
        });
        stage.addActor(restartButton);

        settingButton = (new ImageButtonBuilder()).buildTexture("setting.png").buildButton();
        settingButton.setPosition(Gdx.graphics.getWidth()/2-110, Gdx.graphics.getHeight()/2 - 300);
        settingButton.setSize(200, 200);
        settingButton.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                modify();
            }
        });
        stage.addActor(settingButton);

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
        renderer.render(delta);
//        update(delta);
//
//        Gdx.gl.glClearColor(0,0,0,1);
//        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//
//        RetroMania.getRetroManiaInstance().sb.begin();
//        background.draw(RetroMania.getRetroManiaInstance().sb, delta);
//
//        RetroMania.getRetroManiaInstance().sb.end();
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


