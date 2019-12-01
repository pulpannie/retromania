package com.retromania.game.special_mario.abstractions;

public interface DeathObservable {
    void updateDeathObservers();
    void addDeathObserver(DeathObserver deathObserver);
}
