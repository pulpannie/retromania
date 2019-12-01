package com.retromania.game.special_mario.models.utils;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

//TODO add sub component system so that only the presenters can have access to this
@Module
public abstract class LevelPreferenceProvider {
    @Singleton
    @Binds
    abstract LevelPreference getLevelPrefrence(LevelPreferenceMario levelPreferenceMario);
}
