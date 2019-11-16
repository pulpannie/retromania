package com.retromania.game.spaceship_shooter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.spaceship_shooter.SpaceShipShooterStarter;
import com.retromania.game.spaceship_shooter.individuals.Background;
import com.retromania.game.spaceship_shooter.individuals.GameStats;
import com.retromania.game.spaceship_shooter.individuals.ImageButtonBuilder;
import com.retromania.game.spaceship_shooter.individuals.LabelBuilder;

public class MenuScreen implements Screen {
    private RetroManiaGame game;
    private OrthographicCamera gamecam;
    private Viewport gamePort;
    private ImageButton startButton;
    private Background background;
    public Stage stage;
    Label highScoreTextLabel;
    Label latestScoreTextLabel;
    Label highScoreLabel;
    Label latestScoreLabel;
    GameStats temp;
    Table table;
    MainScreenInterface mainscreen;

    public MenuScreen(RetroManiaGame game, MainScreenInterface mainscreen){
        this.game = game;
        gamecam = new OrthographicCamera();
        gamePort = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), gamecam);
        this.mainscreen = mainscreen;

        stage = new Stage(gamePort, game.sb);
        background = new Background();
        temp = SpaceShipShooterStarter.getGameStats();
        table = new Table();
        table.top();
        table.setFillParent(true);


        LabelBuilder labelBuilder = new LabelBuilder();
        labelBuilder.buildFont(6.0f).buildColor().buildLabelStyle();

        highScoreTextLabel = labelBuilder.buildText("High Score").buildLabel();
        latestScoreTextLabel = labelBuilder.buildText("Latest Score").buildLabel();
        highScoreLabel = labelBuilder.buildText(String.format("%03d", temp.getHighScore())).buildLabel();
        latestScoreLabel = labelBuilder.buildText(String.format("%06d", temp.getLatestScore())).buildLabel();


        table.add(highScoreTextLabel).expandX().pad(10);
        table.add(highScoreLabel).expandX();
        table.row();
        table.add(latestScoreTextLabel).expandX();
        table.add(latestScoreLabel).expandX().pad(10);



        startButton = (new ImageButtonBuilder()).buildTexture("start_button.png").buildButton();
        startButton.setPosition(Gdx.graphics.getWidth()/2-400, Gdx.graphics.getHeight()/2 - 600);
        startButton.setSize(800, 800);

        stage.addActor(startButton);
        stage.addActor(table);

        Gdx.input.setInputProcessor(stage);

    }
    @Override
    public void show() {
        stage.addActor(startButton);
        stage.addActor(table);
        highScoreLabel.setText(String.format("%03d", temp.getHighScore()));
        latestScoreLabel.setText(String.format("%03d", temp.getLatestScore()));

        Gdx.input.setInputProcessor(stage);
    }

    public void handleInput(float dt){
        if (startButton.isPressed()) {
            start();
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
    }

    public void start(){
        stage.dispose();
        mainscreen.restart();
    }
    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}

