package com.retromania.game.mario.abstractions;

public interface RestartableObservable {
  void updateRestartableObservers();

  void addRestartableObserver(RestartableObserver restartableObserver);
}
