package com.retromania.game.spaceship_shooter.presenters;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.retromania.game.spaceship_shooter.SpaceShipShooterStarter;
import com.retromania.game.spaceship_shooter.models.LabelBuilder;
import com.retromania.game.spaceship_shooter.Views.MainScreenInterface;

import java.util.Locale;

/**
 * Presenter class of MenuScreen. It has access to models of MenuScreen and updates models by
 * command of view class
 *
 * @author Umid, Thuy
 */
public class MenuScreenPresenter extends Presenter {
  /** Interface to give input to viewport of starter class */
  private MainScreenInterface mainScreen;
  /** Label that shows up high score */
  private Label highScoreLabel;
  /** Label that shows up latest score */
  private Label latestScoreLabel;
    /** Stage that holds table of labels to print */
  private Stage stage;

  /**
   * Constructor class for MenuScreenPresenter
   *
   * @param mainScreen access to viewport of starter class through interface
   */
  public MenuScreenPresenter(MainScreenInterface mainScreen) {
    super();
    // Label that shows up word "High Score"
    Label highScoreTextLabel;
    // Label that shows up word "Latest Score"
    Label latestScoreTextLabel;
    // Table that stores labels
    Table table;

    this.mainScreen = mainScreen;

    LabelBuilder labelBuilder = new LabelBuilder();
    labelBuilder.buildFont(6.0f).buildColor().buildLabelStyle();

    highScoreTextLabel = labelBuilder.buildText("High Score").buildLabel();
    latestScoreTextLabel = labelBuilder.buildText("Latest Score").buildLabel();
    highScoreLabel =
        labelBuilder
            .buildText(
                String.format(
                    Locale.US, "%03d", SpaceShipShooterStarter.getGameStats().getHighScore()))
            .buildLabel();
    latestScoreLabel =
        labelBuilder
            .buildText(
                String.format(
                    Locale.US, "%06d", SpaceShipShooterStarter.getGameStats().getLatestScore()))
            .buildLabel();

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

  /** dispose the menu screen */
  public void dispose() {}

  /** update the menu screen */
  public void update(float dt) {
    super.update(dt);
  }

  /** start the game */
  public void start() {
    mainScreen.restart();
  }

  /** update the highScore and the latest score. */
  public void updateTable() {
    highScoreLabel.setText(
        String.format(Locale.US, "%03d", SpaceShipShooterStarter.getGameStats().getHighScore()));
    latestScoreLabel.setText(
        String.format(Locale.US, "%03d", SpaceShipShooterStarter.getGameStats().getLatestScore()));
  }

  /**
   * get the stage
   *
   * @return the stage
   */
  public Stage getStage() {
    return stage;
  }
}
