package com.retromania.game.special_mario;

import com.retromania.game.special_mario.utils.TextureAtlasLoader;
import com.retromania.game.special_mario.utils.WorldManager;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {WorldManager.class, TextureAtlasLoader.class})
public interface SpecialMarioStarterCreator {
  SpecialMarioStarter getSpecialMarioStarter();

  @Component.Builder
  interface Builder {

    @BindsInstance
    Builder MainPlayerTextureRegionName(@Named("Texture Region Name") String textureRegionName);

    @BindsInstance
    Builder MainPlayerX(@Named("X") int x);

    @BindsInstance
    Builder MainPlayerY(@Named("Y") int y);

    @BindsInstance
    Builder MainPlayerINITX(@Named("INIT X IN WORLD") int X);

    @BindsInstance
    Builder MainPlayerINITY(@Named("INIT Y IN WORLD") int Y);

    SpecialMarioStarterCreator build();
  }
}
