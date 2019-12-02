package com.retromania.game.mario;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.mario.models.utils.LevelPreferenceProvider;
import com.retromania.game.mario.utils.OrthogonalTiledMapRendererProvider;
import com.retromania.game.mario.utils.TextureAtlasLoader;
import com.retromania.game.mario.utils.WorldManager;
import com.retromania.game.mario.views.SpecialMarioStarter;
import com.retromania.game.mario.views.renderables.UserRenderPreferenceProvider;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(
    modules = {
      WorldManager.class,
      TextureAtlasLoader.class,
      UserRenderPreferenceProvider.class,
      LevelPreferenceProvider.class,
      OrthogonalTiledMapRendererProvider.class
    })
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

    @BindsInstance
    Builder MusicAddress(@Named("Music Name") String musicName);

    @BindsInstance
    Builder setScale(@Named("unit scacle") float scale);

    @BindsInstance
    Builder setGameCam(@Named("Super Mario Game Cam") OrthographicCamera gameCam);

    @BindsInstance
    Builder setGamePort(@Named("Super Mario Game Port") Viewport gamePort);


    @BindsInstance
    Builder setGameName(@Named("name Of Game") String nameOfGame);

    SpecialMarioStarterCreator build();
  }
}
