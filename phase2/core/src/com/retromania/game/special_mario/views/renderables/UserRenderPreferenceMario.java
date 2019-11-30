package com.retromania.game.special_mario.views.renderables;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import javax.inject.Inject;

class UserRenderPreferenceMario implements UserRenderPreference {

  private GameRenderer gameRenderer;
  private MarioRenderable selectedRenderer;
  private Map<Supplier<MarioRenderable>, String> functionOfRenderModeMap = new HashMap<>();
  @Inject
  UserRenderPreferenceMario(
      GameRenderer gameRenderer) {
      this.gameRenderer = gameRenderer;


    setGameRenderNormal();
    functionOfRenderModeMap.put(this::setGameRenderNormal, "NORMAL GAME");
    functionOfRenderModeMap.put(this::setGameRenderNormal, "SURVIVAL GAME");
    functionOfRenderModeMap.put(this::setGameRenderGhost, "GHOST GAME");

  }

  @Override
  public Map<Supplier<MarioRenderable>, String> getRenderModeFunctions() {
    return functionOfRenderModeMap;
  }

  @Override
  public MarioRenderable setGameRenderNormal() {
    this.selectedRenderer = gameRenderer;
    return selectedRenderer;
  }

  @Override
  public MarioRenderable setGameRenderSurvival() {
    throw new RuntimeException("this renderer has not been implemented yet.");
  }

  @Override
  public MarioRenderable setGameRenderGhost() {
    throw new RuntimeException("this renderer has not been implemented yet.");
  }

  @Override
  public MarioRenderable getRenderable() {
    return selectedRenderer;
  }

}
