package com.retromania.game.mario.utils;


import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class OrthogonalTiledMapRendererProvider {
    @Singleton
    @Provides
    OrthogonalTiledMapRenderer getOrthoRenderer(@Named("unit scacle") float scale){
        return new OrthogonalTiledMapRenderer(new TiledMap(), scale);
    }
}
