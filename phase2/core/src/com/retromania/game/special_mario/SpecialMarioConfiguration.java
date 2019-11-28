package com.retromania.game.special_mario;


import javax.inject.Singleton;

public class SpecialMarioConfiguration {

    public static float convertPixelToMeter(float meter) {
      return meter * getPixelToMeterConversionRate();
    }

    public static float getPixelToMeterConversionRate() {
      return 1 / 100f;
    }
}
