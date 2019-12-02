package com.retromania.game.mario.utils;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;

import dagger.Module;
import dagger.Provides;

@Module
public class TextureAtlasLoader {
    @Provides
    public TextureAtlas getTextureAtlas(){
        return new TextureAtlas("special_mario/mario_small.pack");
    }
}
