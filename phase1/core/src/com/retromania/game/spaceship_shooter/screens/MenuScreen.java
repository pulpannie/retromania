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

    public MenuScreen(RetroManiaGame game){
        this.game = game;
        gamecam = new OrthographicCamera();
        gamePort = new StretchViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), gamecam);

        stage = new Stage(gamePort, game.sb);
        background = new Background();
        temp = SpaceShipShooterStarter.gameStats;
        table = new Table();
        table.top();
        table.setFillParent(true);

        BitmapFont sampleFont = new BitmapFont();
        sampleFont.getData().setScale(6.0f);
        Color sampleColor = Color.WHITE;

        highScoreTextLabel = new Label("High Score", new Label.LabelStyle(sampleFont, sampleColor));
        latestScoreTextLabel = new Label("Latest Score", new Label.LabelStyle(sampleFont, sampleColor));
        highScoreLabel = new Label(String.format("%03d", temp.getHighScore()), new Label.LabelStyle(sampleFont, sampleColor));
        latestScoreLabel = new Label(String.format("%06d", temp.getLatestScore()), new Label.LabelStyle(sampleFont, sampleColor));

        table.add(highScoreTextLabel).expandX().pad(10);
        table.add(highScoreLabel).expandX();
        table.row();
        table.add(latestScoreTextLabel).expandX();
        table.add(latestScoreLabel).expandX().pad(10);


        Texture startTexture = new Texture("start_button.png");
        TextureRegion startTextureRegion =  new TextureRegion(startTexture);
        TextureRegionDrawable startTextureRegionDrawable = new TextureRegionDrawable(startTextureRegion);
        startButton = new ImageButton(startTextureRegionDrawable);
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

