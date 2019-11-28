package com.retromania.game.special_mario;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component
public interface SpecialMarioStarterCreator {
    SpecialMarioStarter getSpecialMarioStarter();
}
