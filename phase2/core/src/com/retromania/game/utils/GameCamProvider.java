package com.retromania.game.utils;


import com.badlogic.gdx.graphics.OrthographicCamera;

import dagger.Module;
import dagger.Provides;

@Module
public class GameCamProvider {

    @Provides
    public OrthographicCamera getGameCam(){
        return new OrthographicCamera();
    }

}
