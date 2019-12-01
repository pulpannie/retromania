package com.retromania.game.special_mario.views.renderables;



import dagger.Binds;
import dagger.Module;


/**
 *
 * Provides an instance of the UserRenderPreference
 *
 * **/
@Module
public abstract class UserRenderPreferenceProvider {

    @Binds
    abstract UserRenderPreference getUserPrefrence(UserRenderPreferenceMario userRenderPreferenceMario);

}
