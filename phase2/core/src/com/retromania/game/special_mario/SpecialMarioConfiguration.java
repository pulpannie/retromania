package com.retromania.game.special_mario;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.special_mario.views.SpecialMarioStarter;

public class SpecialMarioConfiguration {

  public static final SpecialMarioStarter FINAL_GAME;

  static {
    OrthographicCamera gameCam = new OrthographicCamera();
    Viewport gamePort =
        new FitViewport(
            SpecialMarioConfiguration.convertPixelToMeter(RetroManiaGame.V_WIDTH),
            SpecialMarioConfiguration.convertPixelToMeter(RetroManiaGame.V_HEIGHT),
            gameCam);
    gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
    FINAL_GAME =
        DaggerSpecialMarioStarterCreator.builder()
            .MainPlayerTextureRegionName("mario_small")
            .MainPlayerX(0)
            .MainPlayerY(0)
            .MainPlayerINITX(32)
            .MainPlayerINITY(256)
            .MusicAddress("special_mario/marioFirstLevelMusic.ogg")
            .setScale(getPixelToMeterConversionRate())
            .setGameCam(gameCam)
            .setGamePort(gamePort)
            .build()
            .getSpecialMarioStarter();
  }

  public static float convertPixelToMeter(float meter) {
    return meter * getPixelToMeterConversionRate();
  }

  public static float getPixelToMeterConversionRate() {
    return 1 / 100f;
  }
}
