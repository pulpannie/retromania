package com.retromania.game.special_mario.abstractions;

public interface FinisherObservable {
    void updateFinisherObservers();
    void addFinisherObserver(FinisherObserver finisherObserver);
}
