package com.retromania.game.special_mario.models.utils;

import dagger.Binds;
import dagger.Module;

//TODO add sub component system so that only the presenters can have access to this
@Module
public abstract class LevelPreferenceProvider {
    @Binds
    abstract LevelPreference getLevelPrefrence(LevelPreferenceMario levelPreferenceMario);
}
