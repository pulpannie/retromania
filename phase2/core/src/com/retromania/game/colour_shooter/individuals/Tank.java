package com.retromania.game.colour_shooter.individuals;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Tank {

    private Texture correctTank;

    public Tank(int tankPref){
        StringBuilder strPath = new StringBuilder("colour_shooter/cannon");
        strPath.append(tankPref);
        strPath.append(".png");
        correctTank = new Texture(Gdx.files.internal(strPath.toString()));
    }

    public Texture getTankTexture(){
        return correctTank;
    }
}
