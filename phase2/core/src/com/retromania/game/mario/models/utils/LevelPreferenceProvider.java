package com.retromania.game.mario.models.utils;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

/**
 * This class is responsible for providing us an implementation of levelPreference
 *
 * <p>*
 */
// TODO add sub component system so that only the presenters can have access to this
@Module
public abstract class LevelPreferenceProvider {
  @Singleton
  @Binds
  abstract LevelPreference getLevelPrefrence(LevelPreferenceMario levelPreferenceMario);
}
