package com.retromania.game.shared_abstractions;

public interface RetroManiaModel<T> {
  void update();

  void setInput(T input);

  T getOutput();
}
