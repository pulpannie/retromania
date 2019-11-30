package com.retromania.game.spaceship_shooter.individuals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
/**
 * Class that stores information about instant score, time, and area
 *
 * @author Umid, Thuy
 **/
public class Hud {
    /**
     * Variables:
     * stage: Stage that our table of labels added
     * viewport: view screen
     * worldTimer: timer that counts down to 0
     * timeCount: timer counts up to 1
     * score: variable that stores how many ufos shooted
     * countDownLabel: label that draws time left
     * scoreLabel: label that draws score
     * timeLabel: label that draws "TIMER"
     * worldLabel: label that draws name of specified world
     * scoreNameLabel: label that draws "SCORE"
     * areaLabel:  label that draws "AREA"
     * */
    public  Stage stage;
    private Viewport viewport;

    private Integer worldTimer;
    private float timeCount;
    private Integer score;

    private Label countDownLabel;
    private Label scoreLabel;
    private Label timeLabel;
    private Label worldLabel;
    private Label scoreNameLabel;
    private Label areaLabel;
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
        countDownLabel = labelBuilder.buildText(String.format("%03d", worldTimer)).buildLabel();
        scoreLabel = labelBuilder.buildText(String.format("%06d", score)).buildLabel();
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
     * Method that updates time left
     * */
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
    /**
     * Method that updates score
     * */
    void addScore(int value) {
        score += value;
        scoreLabel.setText(String.format("%06d", score));
    }
}
