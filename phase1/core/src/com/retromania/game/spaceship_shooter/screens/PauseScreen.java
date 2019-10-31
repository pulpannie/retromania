package com.retromania.game.spaceship_shooter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.spaceship_shooter.SpaceShipShooterStarter;
import com.retromania.game.spaceship_shooter.individuals.Background;

public class PauseScreen implements Screen {
    private RetroManiaGame game;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private ImageButton resumeButton;
    private ImageButton exitButton;
    private ImageButton restartButton;
    private Background background;
    public Stage stage;

    public PauseScreen(RetroManiaGame game){
        this.game = game;
        gamecam = new OrthographicCamera();
        gamePort = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), gamecam);

        stage = new Stage(gamePort, game.sb);
        background = new Background();


        Texture resumeTexture = new Texture("resume.png");
        TextureRegion resumeTextureRegion =  new TextureRegion(resumeTexture);
        TextureRegionDrawable resumeTextureRegionDrawable = new TextureRegionDrawable(resumeTextureRegion);
        resumeButton = new ImageButton(resumeTextureRegionDrawable);
        resumeButton.setPosition(Gdx.graphics.getWidth()/2-225, Gdx.graphics.getHeight()/2+200);
        resumeButton.setSize(450, 450);
        stage.addActor(resumeButton);

        Texture restartTexture = new Texture("restart.png");
        TextureRegion restartTextureRegion =  new TextureRegion(restartTexture);
        TextureRegionDrawable restartTextureRegionDrawable = new TextureRegionDrawable(restartTextureRegion);
        restartButton = new ImageButton(restartTextureRegionDrawable);
        restartButton.setPosition(Gdx.graphics.getWidth()/2-225, Gdx.graphics.getHeight()/2 - 400);
        restartButton.setSize(450, 450);
        stage.addActor(restartButton);



        Gdx.input.setInputProcessor(stage);

    }
    @Override
    public void show() {
        stage.addActor(resumeButton);
        stage.addActor(restartButton);
        Gdx.input.setInputProcessor(stage);
    }

    public void handleInput(float dt){
        if (resumeButton.isPressed()) {
            resume();
        }
        else if(restartButton.isPressed()){
            restart();
        }
    }

    public void update(float dt){
        handleInput(dt);

        gamecam.update();
    }

    @Override
    public void render(final float delta) {
        update(delta);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        game.sb.begin();
        background.draw(game.sb, delta);

        game.sb.end();
        stage.draw();


    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
        stage.dispose();
        game.setScreen(SpaceShipShooterStarter.getPlayScreen());
    }

    public void restart(){
        stage.dispose();
        SpaceShipShooterStarter.setPlayScreen(new PlayScreen(game));
        game.setScreen(SpaceShipShooterStarter.getPlayScreen());
    }
    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}


