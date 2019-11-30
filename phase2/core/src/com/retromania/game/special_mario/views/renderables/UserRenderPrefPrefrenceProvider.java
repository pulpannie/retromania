package com.retromania.game.special_mario.views.renderables;



import dagger.Binds;
import dagger.Module;

@Module
public abstract class UserRenderPrefPrefrenceProvider {

    @Binds
    abstract UserRenderPreference getUserPrefrence(UserRenderPreferenceMario userRenderPreferenceMario);

}
