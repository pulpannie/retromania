package com.retromania.game.spaceship_shooter.presenters;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.retromania.game.RetroMania;
import com.retromania.game.spaceship_shooter.SpaceShipShooterStarter;
import com.retromania.game.spaceship_shooter.individuals.Car;
import com.retromania.game.spaceship_shooter.individuals.Hud;
import com.retromania.game.spaceship_shooter.individuals.UfoManager;
import com.retromania.game.spaceship_shooter.screens.MainScreenInterface;

import java.util.ArrayList;

public class PlayScreenPresenter extends Presenter{

    private Hud hud;
    private Car car;
    private UfoManager ufoManager;
    private MainScreenInterface mainScreen;
    private boolean finished = false;

    public PlayScreenPresenter(String screenType, int numOfUfos, MainScreenInterface mainScreen){
        super(screenType);
        hud = new Hud(RetroMania.getRetroManiaInstance().sb);
        car = new Car();
        ufoManager = new UfoManager(numOfUfos);
        this.mainScreen = mainScreen;

    }
    public void moveCarRight(){
        car.moveRight();
    }
    public void moveCarLeft(){
        car.moveLeft();
    }
    public void pause(){
        mainScreen.pause();
    }

    public void endGame(){
        SpaceShipShooterStarter.getGameStats().update(hud.getScore());
        this.mainScreen.saveScore(SpaceShipShooterStarter.getGameStats().getHighScore());
        this.mainScreen.returnMenu();
        mainScreen.save();
    }

    public void dispose(){

    }

    public void update(float dt){
        super.update(dt);
        RetroMania.getRetroManiaInstance().sb.setProjectionMatrix(hud.stage.getCamera().combined);
        if (hud.countDown(dt))
            finished = true;
        ufoManager.update(car.getiRocket(), hud);


    }
    public boolean isFinished(){return finished;}

    public void shoot(){car.shoot();}

    public Stage getHudStage(){return hud.stage;}

    public ArrayList<Actor> getRenderableActors(){
        ArrayList<Actor> list = new ArrayList<Actor>();
        list.add(ufoManager);
        list.add(car);
        if (car.shot())
            list.add(car.getiRocket());
        return list;
    }


}
