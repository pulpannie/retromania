package com.retromania.game.spaceship_shooter.individuals;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.retromania.game.shared_abstractions.RetroManiaGame;

import java.util.List;

/**
 * The UFO drawer responsible for drawing a list of UFOs.
 * @author Thuy, Umid.
 */
public class UfoDrawer {

    /**
     * Draw the lhe list of UFOs.
     * @param batch
     * @param ufos   the given list of UFOs.
     * @param delta
     */
    public void drawUfos(Batch batch, List<UFO> ufos, float delta){
        for (UFO i: ufos)
            i.draw(batch, delta);
    }
}
