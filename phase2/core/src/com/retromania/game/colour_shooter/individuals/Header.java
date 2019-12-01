package com.retromania.game.colour_shooter.individuals;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Locale;

public class Header {
    public Viewport viewport;
    private Label colourTracker;
    private Integer worldTimer = 30;
    private float timeCount;
    private Label timeTracker;
    private boolean endGame = false;

    public Header(Stage stage, float width, float height) {
        Integer worldTimer = 30;
        timeCount = 0;
        Integer score = 0;

        viewport = new FillViewport(width, height, new OrthographicCamera());
        Table table = new Table();
        table.top();
        table.setFillParent(true);

        Label scoreText = new Label(String.format(Locale.US, "SCORE"), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        Label colourText = new Label(String.format(Locale.US,"COLOUR"), new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        Label timeText = new Label(String.format("TIME"), new Label.LabelStyle(new BitmapFont(), Color.BLACK));

        Label scoreTracker = new Label(String.format(Locale.US,"%03d", score), new Label.LabelStyle(new BitmapFont(), Color.BLACK));

        colourTracker = new Label("NOT SET", new Label.LabelStyle(new BitmapFont(), Color.BLACK));
        setRandomColour();

        timeTracker = new Label(String.format(Locale.US,"%02d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.BLACK));



        table.add(scoreText).expandX().padTop(10);
        table.add(colourText).expandX().padTop(10);
        table.add(timeText).expandX().padTop(10);
        table.row();
        table.add(scoreTracker).expandX();
        table.add(colourTracker).expandX();
        table.add(timeTracker).expandX();

        stage.addActor(table);
    }

    public void countDown(float dt) {
        timeCount += dt;
        if (timeCount >= 1 & worldTimer > 0) {
            worldTimer--;
            timeTracker.setText(String.format(Locale.US, "%02d", worldTimer));
            timeCount = 0;
            if (worldTimer <= 10) {
                timeTracker.setStyle(new Label.LabelStyle(new BitmapFont(), Color.RED));
            }
        } else if (worldTimer <= 0) {
            endGame = true;
        }
    }

    public boolean checkGameOver() {
        return endGame;
    }

    public void update(float dt) {
        countDown(dt);
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
