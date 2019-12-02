package com.retromania.game.colour_shooter.individuals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

/**
 * THIS CLASS IS RESPONSIBLE FOR MAKING THE TANK WHICH SHOOTS THE BULLET AT THE BOTTOM OF THE
 * SCREEN.
 */
public class Tank {

    private Texture correctTank;

    /**
     * @param tankPref: GETS THE INITIAL TANK PREFERENCE
     */
    public Tank(int tankPref) {
        String strPath = "colour_shooter/cannon" + tankPref + ".png";
        correctTank = new Texture(Gdx.files.internal(strPath));
    }

    public Texture getTankTexture() {
        return correctTank;
    }
}
