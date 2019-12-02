package com.retromania.game.spaceship_shooter.presenters;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.retromania.game.RetroMania;
import com.retromania.game.spaceship_shooter.SpaceShipShooterStarter;
import com.retromania.game.spaceship_shooter.models.Car;
import com.retromania.game.spaceship_shooter.models.Hud;
import com.retromania.game.spaceship_shooter.models.UfoManager;
import com.retromania.game.spaceship_shooter.views.MainScreenInterface;

import java.util.ArrayList;

/**
 * Presenter class of PlayScreen. It has access to models of MenuScreen and updates models by
 * command of view class
 *
 * @author Thuy, Umid
 */
public class PlayScreenPresenter extends Presenter {
  /** hud that stores time left and score of current game */
  private Hud hud;
  /** Main player of game */
  private Car car;
  /** manager that handles changes in Ufo and Rocket */
  private UfoManager ufoManager;
  /** Interface to give input to viewport of starter class */
  private MainScreenInterface mainScreen;
  /** boolean that stores info about whether game finished or not */
  private boolean finished = false;

  /**
   * Constructor class for PlayScreenPresenter
   *
   * @param numOfUfos maximum count of ufos we want to show up on Screen
   * @param mainScreen access to viewport of starter class through interface
   */
  public PlayScreenPresenter(int numOfUfos, MainScreenInterface mainScreen) {
    super();
    hud = new Hud(RetroMania.getRetroManiaInstance().sb);
    car = new Car();
    ufoManager = new UfoManager(numOfUfos);
    this.mainScreen = mainScreen;
  }

  /** Method that request car/model to move right */
  public void moveCarRight() {
    car.moveRight();
  }

  /** Method that request car/model to move left */
  public void moveCarLeft() {
    car.moveLeft();
  }

  /** Changes Screen from Play to Pause */
  public void pause() {
    mainScreen.pause();
  }

  /** Changes Screen from Play to Menu and saves current score */
  public void endGame() {
    SpaceShipShooterStarter.getGameStats().update(hud.getScore());
    this.mainScreen.saveScore(SpaceShipShooterStarter.getGameStats().getHighScore());
    this.mainScreen.returnMenu();
    mainScreen.save();
  }

  /** Does request hud/model to countdown, check if game finished, and updates ufoManager */
  public void update(float dt) {
    super.update(dt);
    RetroMania.getRetroManiaInstance().sb.setProjectionMatrix(hud.stage.getCamera().combined);
    if (hud.countDown(dt)) finished = true;
    ufoManager.update(car.getRocket(), hud);
  }

  /** @return boolean about whether game finished or not */
  public boolean isFinished() {
    return finished;
  }

  /** Requests car/model to shoot rocket */
  public void shoot() {
    car.shoot();
  }

  /**
   * getter for stage of hud
   *
   * @return stage that has score and time labels
   */
  public Stage getHudStage() {
    return hud.stage;
  }

  /**
   * Puts renderable actors to list and returns it
   *
   * @return list of actors
   */
  public ArrayList<Actor> getRenderableActors() {
    ArrayList<Actor> list = new ArrayList<>();
    list.add(ufoManager);
    list.add(car);
    if (car.shot()) list.add(car.getRocket());
    return list;
  }
}
