package com.retromania.game.special_mario;

import javax.inject.Inject;

import dagger.Component;

@Component
public interface SpecialMarioStarterCreator {
    SpecialMarioStarter getSpecialMarioStarter();
}
