package com.retromania.game.special_mario;

public class SpecialMarioConfiguration {

  static public final SpecialMarioStarter FINAL_GAME =
      DaggerSpecialMarioStarterCreator.create().getSpecialMarioStarter();

  public static float convertPixelToMeter(float meter) {
    return meter * getPixelToMeterConversionRate();
  }

  public static float getPixelToMeterConversionRate() {
    return 1 / 100f;
  }
}
