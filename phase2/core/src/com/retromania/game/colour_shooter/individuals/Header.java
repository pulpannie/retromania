package com.retromania.game.colour_shooter.individuals;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Header {
    public Viewport viewport;

    private Integer worldTimer;
    private float timecount;
    private Integer score;

    Label scoreText;
    Label colourText;
    Label timeText;
    Label scoreTracker;
    Label colourTracker;
    Label timeTracker;

    public Header(Stage stage, float width, float height) {
        worldTimer = 30;
        timecount = 0;
        score = 0;

        viewport = new FillViewport(width, height, new OrthographicCamera());
        Table table = new Table();
        table.top();
        table.setFillParent(true);

        scoreText = new Label(String.format("SCORE"), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        colourText = new Label(String.format("COLOUR"), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        timeText = new Label(String.format("TIME"), new Label.LabelStyle(new BitmapFont(), Color.BLACK));

        colourTracker = new Label("NOT SET", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        setRandomColour();

        timeTracker = new Label(String.format("%02d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        scoreTracker = new Label(String.format("%03d", score), new Label.LabelStyle(new BitmapFont(), Color.BLACK));

        table.add(scoreText).expandX().padTop(10);;
        table.add(colourText).expandX().padTop(10);;
        table.add(timeText).expandX().padTop(10);;
        table.row();
        table.add(scoreTracker).expandX();
        table.add(colourTracker).expandX();
        table.add(scoreTracker).expandX();

        stage.addActor(table);
    }

    public void setRandomColour() {
        int randomNumber1 = (int) (Math.random() * 4);
        String[] arrayColorText = {"RED", "GREEN", "BLUE", "YELLOW"};
        Color[] arrayColorObject = {new Color().set(255, 0, 0, 1),
                new Color().set(255, 255, 0, 1),
                new Color().set(0, 176, 240, 1),
                new Color().set(0, 176, 80, 1)};
        colourTracker.setText(arrayColorText[randomNumber1]);

        int randomNumber2 = (int) (Math.random() * 4);

        colourTracker.setStyle(new Label.LabelStyle(new BitmapFont(),
                arrayColorObject[randomNumber2]));
    }
}
