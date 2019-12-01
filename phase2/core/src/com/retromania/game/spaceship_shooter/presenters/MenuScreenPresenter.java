package com.retromania.game.spaceship_shooter.presenters;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.retromania.game.spaceship_shooter.SpaceShipShooterStarter;
import com.retromania.game.spaceship_shooter.individuals.LabelBuilder;
import com.retromania.game.spaceship_shooter.screens.MainScreenInterface;

import java.util.Locale;


public class MenuScreenPresenter extends Presenter {

    private MainScreenInterface mainScreen;
    private Label highScoreLabel;
    private Label latestScoreLabel;
    private Stage stage;

    public MenuScreenPresenter(String screenType, MainScreenInterface mainScreen){
        super(screenType);

        Label highScoreTextLabel;
        Label latestScoreTextLabel;

        Table table;

        this.mainScreen = mainScreen;

        LabelBuilder labelBuilder = new LabelBuilder();
        labelBuilder.buildFont(6.0f).buildColor().buildLabelStyle();

        highScoreTextLabel = labelBuilder.buildText("High Score").buildLabel();
        latestScoreTextLabel = labelBuilder.buildText("Latest Score").buildLabel();
        highScoreLabel = labelBuilder.buildText(String.format(Locale.US,"%03d", SpaceShipShooterStarter.getGameStats().getHighScore())).buildLabel();
        latestScoreLabel = labelBuilder.buildText(String.format(Locale.US,"%06d", SpaceShipShooterStarter.getGameStats().getLatestScore())).buildLabel();


        table = new Table();
        table.top();
        table.setFillParent(true);
        table.add(highScoreTextLabel).expandX().pad(10);
        table.add(highScoreLabel).expandX();
        table.row();
        table.add(latestScoreTextLabel).expandX();
        table.add(latestScoreLabel).expandX().pad(10);

        stage = new Stage();
        stage.addActor(table);
    }

    public void dispose() {

    }

    public void update(float dt){
        super.update(dt);
    }

    public void start() {
        mainScreen.restart();
    }
    public void updateTable(){
        highScoreLabel.setText(String.format(Locale.US,"%03d", SpaceShipShooterStarter.getGameStats().getHighScore()));
        latestScoreLabel.setText(String.format(Locale.US,"%03d", SpaceShipShooterStarter.getGameStats().getLatestScore()));
    }
    public Stage getStage(){return stage;}

}
