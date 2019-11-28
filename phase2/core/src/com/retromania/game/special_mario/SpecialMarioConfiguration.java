package com.retromania.game.special_mario;

public class SpecialMarioConfiguration {

  public static final SpecialMarioStarter FINAL_GAME;

  static {
    FINAL_GAME =
        DaggerSpecialMarioStarterCreator.builder()
            .MainPlayerTextureRegionName("mario_small")
            .MainPlayerX(0)
            .MainPlayerY(0)
            .MainPlayerINITX(32)
            .MainPlayerINITY(256)
            .MusicAddress("special_mario/marioFirstLevelMusic.ogg")
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
