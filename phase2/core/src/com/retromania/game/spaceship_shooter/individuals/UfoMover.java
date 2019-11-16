package com.retromania.game.spaceship_shooter.individuals;

import java.util.List;

public class UfoMover {

     public void moveUfos(List<UFO> ufos){
        for (UFO i: ufos)
            i.moveRight();
    }

    public void moveRocket(Rocket rocket, List<UFO> ufos, Hud hud){

        if (rocket != null && !rocket.reach_top()) {
            rocket.moveUp();
            for (UFO i : ufos)
            if (i.isRocketTouches(rocket)) {
              hud.addScore(10);
              i.recreate();
            }
        }
    }


}
