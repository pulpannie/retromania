package com.retromania.game.shared_abstractions;

public class Configuration {
  private static String spaceshipDestroyerPreference;
  private static String tictactoePreference;
  public static String colourshooterPreference;
  private static String bestUserUserName;
  private static String bestUserScore;

  static {
    bestUserUserName = "BEST_USER_USER_NAME";
    bestUserScore = "BEST_USER_SCORE";
    tictactoePreference = "Tic Tac Toe";
    colourshooterPreference = "Colour Shooter";
    spaceshipDestroyerPreference = "spaceshipDestroyerStorage";
  }
}
