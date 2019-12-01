package com.retromania.game.spaceship_shooter.Presenters;

import com.retromania.game.spaceship_shooter.SpaceShipShooterStarter;
import com.retromania.game.spaceship_shooter.Models.Background;

public class Presenter {
    private Background background;
    public Presenter(){
        background = new Background();
    }
    public Background getBackground(){return background;}

    public void update(float dt){
        SpaceShipShooterStarter.playMusic();
    }
}
