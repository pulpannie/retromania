package com.retromania.game.mario.abstractions;

public interface FinisherObservable {
  void updateFinisherObservers();

  void addFinisherObserver(FinisherObserver finisherObserver);
}
