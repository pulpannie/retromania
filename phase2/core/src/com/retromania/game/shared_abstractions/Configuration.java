package com.retromania.game.shared_abstractions;

public class Configuration {
    public static String spaceshipDestroyerPreference;
    public static String tictactoePreference;
    public static String colourshooterPreference;
    public static String bestUserUserName;
    public static String bestUserScore;
    static {
        bestUserUserName = "BEST_USER_USER_NAME";
        bestUserScore = "BEST_USER_SCORE";
        tictactoePreference = "Tic Tac Toe";
        colourshooterPreference = "Colour Shooter";
        spaceshipDestroyerPreference = "spaceshipDestroyerStorage";
    }
}
