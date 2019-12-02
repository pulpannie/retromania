package com.retromania.game.colour_shooter.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.RetroMania;
import com.retromania.game.colour_shooter.ColourShooterStarter;
import com.retromania.game.colour_shooter.presenters.PlayScreenPresenter;
import com.retromania.game.shared_abstractions.RetroManiaScreen;

import java.util.Locale;

public class PlayScreenRetro extends RetroManiaScreen {

    private PlayScreenPresenter presenter;
    private Viewport viewport;
    private Stage stage;
    private float gameWidth, gameHeight;
    private Label scoreTracker;
    private Label colourTracker;
    private Label timeTracker;
    private Table table;

    PlayScreenRetro(ColourShooterStarter game) {
        setUpStage();
        Gdx.input.setInputProcessor(stage);

        presenter = new PlayScreenPresenter(game, gameWidth, gameHeight);
    }

    private void setUpStage() {
        this.gameWidth = 300;
        this.gameHeight = 500;
        OrthographicCamera gameCam = new OrthographicCamera();
        this.viewport = new StretchViewport(gameWidth, gameHeight, gameCam);
        this.viewport.setWorldSize(gameWidth, gameHeight);
        this.stage = new Stage(viewport, RetroMania.getRetroManiaInstance().sb);
    }

    @Override
    public void show() {
        stage.addActor(presenter.setUpBackground());
        stage.addActor(presenter.getTank());
        setupHeader();
        presenter.makeSquare();
    }

    private void setupHeader() {
        table = new Table();
        table.top();
        table.setFillParent(true);
        Integer worldTimer = 30;

        Label scoreText =
                new Label(
                        "SCORE", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        Label colourText =
                new Label(
                        "COLOUR",
                        new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        Label timeText =
                new Label(
                        "TIME", new Label.LabelStyle(new BitmapFont(), Color.BLACK));

        scoreTracker =
                new Label(
                        String.format(Locale.US, "%03d", 0),
                        new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        colourTracker =
                new Label(
                        presenter.getColourText(),
                        new Label.LabelStyle(new BitmapFont(), presenter.getColorObject()));
        timeTracker =
                new Label(
                        String.format(Locale.US, "%02d", worldTimer),
                        new Label.LabelStyle(new BitmapFont(), Color.BLACK));

        table.add(scoreText).expandX().padTop(10);
        table.add(colourText).expandX().padTop(10);
        table.add(timeText).expandX().padTop(10);
        table.row();
        table.add(scoreTracker).expandX();
        table.add(colourTracker).expandX();
        table.add(timeTracker).expandX();

        stage.addActor(table);
    }

    public void update(float dt) {
        super.update();
        presenter.getWorld().step(1 / 60f, 6, 2);
        presenter.getSquare().rotateSquare();
        presenter.updateTime(dt);
        presenter.checkCollided();
        if (presenter.getCollided()) {
            updateHeaderColour();
            updateHeaderScore();
            presenter.setCollision(false);
        } else if (presenter.getBullet() != null) {
            presenter.getBullet().update();
        }
        updateHeaderTime();
    }

    private void updateHeaderColour() {
        colourTracker.setText(presenter.getCurrentColourText());
        colourTracker.setStyle(
                new Label.LabelStyle(new BitmapFont(), presenter.getCurrentColourObject()));
    }

    private void updateHeaderScore() {
        scoreTracker.setText(String.format(Locale.US, "%03d", presenter.getScore()));
    }

    private void updateHeaderTime() {
        int time = presenter.getTime();
        timeTracker.setText(String.format(Locale.US, "%02d", time));
        if (time <= 10) {
            timeTracker.setStyle(new Label.LabelStyle(new BitmapFont(), Color.RED));
        }
        if (time == 0) {
            endGame();
        }
    }

    private void endGame() {
        // NOt enough time to implement
    }

    @Override
    public void render(float delta) {
        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.draw();

        RetroMania.getRetroManiaInstance().sb.begin();
        RetroMania.getRetroManiaInstance().sb.setProjectionMatrix(viewport.getCamera().combined);
        presenter.getSquare().draw(RetroMania.getRetroManiaInstance().sb);
        if (presenter.getBullet() != null) {
            presenter.getBullet().draw(RetroMania.getRetroManiaInstance().sb);
        }

        RetroMania.getRetroManiaInstance().sb.end();

        presenter.b2ddr.render(presenter.getWorld(), viewport.getCamera().combined);
    }

    @Override
    public void handleInput() {
        if (Gdx.input.isTouched()) {
            presenter.justTouched();
        }
    }

    @Override
    public void resize(int width, int height) {
    }
}
