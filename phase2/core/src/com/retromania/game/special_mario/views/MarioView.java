package com.retromania.game.special_mario.views;

import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.shared_abstractions.RetroManiaInnerGame;
import com.retromania.game.special_mario.presenter.MarioGamePresentable;
import com.retromania.game.special_mario.views.renderables.UserRenderPreference;

public abstract class MarioView extends RetroManiaInnerGame {
  public MarioView(
      String name,
      RetroManiaGame.Orientation orientation,
      UserRenderPreference userRenderPreference,
      MarioGamePresentable marioGamePresentable) {
    super(name, orientation);
    userRenderPreference.getRenderable().setPresenter(marioGamePresentable);
  }

}
