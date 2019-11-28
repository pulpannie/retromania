package com.retromania.game.special_mario;

import com.retromania.game.special_mario.utils.LibGdxWorldProvider;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {LibGdxWorldProvider.class})
public interface SpecialMarioStarterCreator {
    SpecialMarioStarter getSpecialMarioStarter();
}
