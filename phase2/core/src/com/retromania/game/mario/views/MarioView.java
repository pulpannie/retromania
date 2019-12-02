package com.retromania.game.mario.views;

import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.shared_abstractions.RetroManiaInnerGame;
import com.retromania.game.mario.presenter.MarioGamePresentable;
import com.retromania.game.mario.views.renderables.UserRenderPreference;

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
