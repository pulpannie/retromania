package com.retromania.game.special_mario.views.renderables;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class UserRenderPreferenceMario implements UserRenderPreference {

  private NormalRenderer gameRenderer;
  private GhostRenderer ghostRenderer;
  private MarioRenderable selectedRenderer;
  private SurvivalRenderer survivalRenderer;
  private Map<Supplier<MarioRenderable>, String> functionOfRenderModeMap = new HashMap<>();

  @Inject
  UserRenderPreferenceMario(
      NormalRenderer gameRenderer, GhostRenderer ghostRenderer, SurvivalRenderer survivalRenderer) {
    this.gameRenderer = gameRenderer;
    this.ghostRenderer = ghostRenderer;
    this.survivalRenderer = survivalRenderer;

    setGameRenderNormal();
    functionOfRenderModeMap.put(this::setGameRenderNormal, "NORMAL GAME");
    functionOfRenderModeMap.put(this::setGameRenderSurvival, "SURVIVAL GAME");
    functionOfRenderModeMap.put(this::setGameRenderGhost, "GHOST GAME");
  }

  public void start() {
    selectedRenderer.start();
  }

  @Override
  public Map<Supplier<MarioRenderable>, String> getRenderModeFunctions() {
    return functionOfRenderModeMap;
  }

  @Override
  public MarioRenderable setGameRenderNormal() {
    this.selectedRenderer = gameRenderer;
    start();
    return selectedRenderer;
  }

  @Override
  public MarioRenderable setGameRenderSurvival() {
    this.selectedRenderer = survivalRenderer;
    start();
    return selectedRenderer;
  }

  @Override
  public MarioRenderable setGameRenderGhost() {
    this.selectedRenderer = ghostRenderer;
    start();
    return selectedRenderer;
  }

  @Override
  public MarioRenderable getRenderable() {
    return selectedRenderer;
  }
}
