package com.retromania.game.spaceship_shooter.individuals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Hud {
    public Stage stage;
    private Viewport viewport;

    private Integer worldTimer;
    private float timeCount;
    private Integer score;

    Label countDownLabel;
    Label scoreLabel;
    Label timeLabel;
    Label worldLabel;
    Label scoreNameLabel;
    Label areaLabel;

    public Integer getScore() {
        return score;
    }

    public Hud(SpriteBatch sb){
        worldTimer = 60;
        timeCount = 0;
        score = 0;
        int width = Gdx.graphics.getWidth();
        int height = Gdx.graphics.getHeight();
        viewport = new StretchViewport(width, height, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        BitmapFont sampleFont = new BitmapFont();
        sampleFont.getData().setScale(3.0f);
        Color sampleColor = Color.WHITE;
        countDownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(sampleFont, sampleColor));
        scoreLabel = new Label(String.format("%06d", score), new Label.LabelStyle(sampleFont, sampleColor));
        timeLabel = new Label("TIMER", new Label.LabelStyle(sampleFont, sampleColor));
        worldLabel = new Label("DESERT", new Label.LabelStyle(sampleFont, sampleColor));
        scoreNameLabel = new Label("SCORE", new Label.LabelStyle(sampleFont, sampleColor));
        areaLabel = new Label("AREA", new Label.LabelStyle(sampleFont, sampleColor));

        table.add(timeLabel).expandX().pad(10);
        table.add(scoreNameLabel).expandX().pad(10);
        table.add(areaLabel).expandX().pad(10);
        table.row();
        table.add(countDownLabel).expandX();
        table.add(scoreLabel).expandX();
        table.add(worldLabel).expandX();
        stage.addActor(table);

    }

    public boolean countDown(float dt){
        timeCount += dt;
        if (timeCount >= 1){
            worldTimer--;
            countDownLabel.setText(String.format("%03d", worldTimer));
            timeCount= 0;
            if (worldTimer <= 15) {
                if (worldTimer % 2 == 0)
                    countDownLabel.setColor(Color.RED);
                else

                    countDownLabel.setColor(Color.WHITE);
            }
        }
        return worldTimer == 0;
    }

    public void addScore(int value) {
        score += value;
        scoreLabel.setText(String.format("%06d", score));
    }
}
