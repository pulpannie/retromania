package com.retromania.game.spaceship_shooter.individuals;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.retromania.game.shared_abstractions.RetroManiaGame;

import java.util.List;

public class UfoDrawer {

    public void drawUfos(Batch batch, List<UFO> ufos, float delta){
        for (UFO i: ufos)
            i.draw(batch, delta);
    }
}
