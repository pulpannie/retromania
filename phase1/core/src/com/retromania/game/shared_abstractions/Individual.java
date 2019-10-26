package com.retromania.game.shared_abstractions;

public abstract class Individual {
    int X, Y, W, H;
    abstract void update(Object ...args);
}
