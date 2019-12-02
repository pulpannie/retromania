package com.retromania.game.mario.abstractions;

public interface DeathObservable {
  void updateDeathObservers();

  void addDeathObserver(DeathObserver deathObserver);
}
