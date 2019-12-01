package com.retromania.game.spaceship_shooter.Presenters;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.retromania.game.RetroMania;
import com.retromania.game.spaceship_shooter.SpaceShipShooterStarter;
import com.retromania.game.spaceship_shooter.Models.Car;
import com.retromania.game.spaceship_shooter.Models.Hud;
import com.retromania.game.spaceship_shooter.Models.UfoManager;
import com.retromania.game.spaceship_shooter.Views.MainScreenInterface;

import java.util.ArrayList;

public class PlayScreenPresenter extends Presenter{

    private Hud hud;
    private Car car;
    private UfoManager ufoManager;
    private MainScreenInterface mainScreen;
    private boolean finished = false;

    public PlayScreenPresenter(int numOfUfos, MainScreenInterface mainScreen){
        super();
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
        ufoManager.update(car.getRocket(), hud);


    }
    public boolean isFinished(){return finished;}

    public void shoot(){car.shoot();}

    public Stage getHudStage(){return hud.stage;}

    public ArrayList<Actor> getRenderableActors(){
        ArrayList<Actor> list = new ArrayList<>();
        list.add(ufoManager);
        list.add(car);
        if (car.shot())
            list.add(car.getRocket());
        return list;
    }


}
