package com.retromania.game.spaceship_shooter.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.Locale;

/**
 * Class that stores information about instant score, time, and area
 *
 * @author Umid, Thuy
 **/
public class Hud {
    /**
     * Stage that our table of labels added
     */
    public  Stage stage;

    /**
     * timer that counts down to 0
     */
    private Integer worldTimer;

    /**
     * timer counts up 1 unit.
     */
    private float timeCount;

    /**
     * variable that stores how many ufos are shot time ten.
     */
    private Integer score;

    /**
     * Label that draws time left
     */
    private Label countDownLabel;

    /**
     * Label that draws score
     */
    private Label scoreLabel;


    /**
     * GETTER METHOD FOR SCORE
     * */
    public Integer getScore() {
        return score;
    }

    /**
     * CONSTRUCTOR CALL FOR Hud class
     * */
    public Hud(SpriteBatch sb){

        // view screen
        Viewport viewport;

        // Label that draws "TIMER"
        Label timeLabel;

        // Label that draws name of specified world
        Label worldLabel;

        // Label that draws "SCORE"
        Label scoreNameLabel;

        // Label that draws "AREA"
        Label areaLabel;

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

        LabelBuilder labelBuilder = new LabelBuilder();
        labelBuilder.buildFont(3.0f).buildColor().buildLabelStyle();
        countDownLabel = labelBuilder.buildText(String.format(Locale.US,"%03d", worldTimer)).buildLabel();
        scoreLabel = labelBuilder.buildText(String.format(Locale.US, "%06d", score)).buildLabel();
        timeLabel = labelBuilder.buildText("TIMER").buildLabel();
        worldLabel = labelBuilder.buildText("DESERT").buildLabel();
        scoreNameLabel = labelBuilder.buildText("SCORE").buildLabel();
        areaLabel = labelBuilder.buildText("AREA").buildLabel();


        table.add(timeLabel).expandX().pad(10);
        table.add(scoreNameLabel).expandX().pad(10);
        table.add(areaLabel).expandX().pad(10);
        table.row();
        table.add(countDownLabel).expandX();
        table.add(scoreLabel).expandX();
        table.add(worldLabel).expandX();
        stage.addActor(table);

    }

    /**
     * Update the remaining time.
     * */
    public boolean countDown(float dt){
        timeCount += dt;
        if (timeCount >= 1){
            worldTimer--;
            countDownLabel.setText(String.format(Locale.US,"%03d", worldTimer));
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
    /**
     * Update score
     * */
    void addScore(int value) {
        score += value;
        scoreLabel.setText(String.format(Locale.US,"%06d", score));
    }
}
