package com.retromania.game.special_mario;

import com.retromania.game.special_mario.utils.LevelManager;
import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {LevelManager.class})
public interface SpecialMarioStarterCreator {
    SpecialMarioStarter getSpecialMarioStarter();
}
