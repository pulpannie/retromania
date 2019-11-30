package com.retromania.game.spaceship_shooter.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.retromania.game.RetroMania;
import com.retromania.game.shared_abstractions.RetroManiaScreen;
import com.retromania.game.spaceship_shooter.SpaceShipShooterStarter;
import com.retromania.game.spaceship_shooter.individuals.GameStats;
import com.retromania.game.spaceship_shooter.individuals.ImageButtonBuilder;
import com.retromania.game.spaceship_shooter.individuals.LabelBuilder;
import com.retromania.game.spaceship_shooter.presenters.MenuScreenPresenter;

public class MenuScreen extends RetroManiaScreen {
    public Stage stage;
    private ImageButton startButton;
    private Label highScoreTextLabel;
    private Label latestScoreTextLabel;
    private Label highScoreLabel;
    private Label latestScoreLabel;
    private GameStats temp;
    private Table table;
    private MenuScreenPresenter presenter;

    public MenuScreen(MainScreenInterface mainscreen){
        presenter = new MenuScreenPresenter("stretch", mainscreen);
        stage = new Stage(presenter.getGamePort(), RetroMania.getRetroManiaInstance().sb);
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

