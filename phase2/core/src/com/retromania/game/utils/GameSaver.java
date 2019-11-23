package com.retromania.game.utils;

import com.badlogic.gdx.Preferences;
import com.retromania.game.RetroMania;
import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.shared_abstractions.RetroManiaGeneralUser;
import com.retromania.game.shared_abstractions.User;

import java.util.HashSet;
import java.util.Set;

public class GameSaver {

  private User currentUser;
  private Preferences preferences;
  private static Set<String> nameOfAllGames = new HashSet<>();
  public static final String BEST_USER_USER_NAME_KEY = "$BEST_USER$NAME$";
  private static final String BEST_USER_SCORE_KEY = "$BEST_USER$SCORE$";

  static boolean alreadyContainsGame(String nameOfGame) {
    return nameOfAllGames.contains(nameOfGame);
  }

  public GameSaver(String nameOfGame, String nameOfUser) {
    if (alreadyContainsGame(nameOfGame)) {
      throw new RuntimeException(
          "A GAME WITH THIS NAME ALREADY EXISTS. " + "PLEASE USE ALREADY CONTAINS GAME FUNCTION");
    }
    nameOfAllGames.add(nameOfGame);
    RetroManiaGame game = RetroMania.getRetroManiaInstance();
    preferences = game.getPrefrences(nameOfGame);
    currentUser = retrieveUser("");
  }

  public GameSaver(String nameOfGame) {
    this(nameOfGame, "");
  }

  public void saveNormalUser(User user) {
    preferences.putInteger(user.getUserName(), user.getScore());
    preferences.flush();
  }

  public void saveBestUser(User user) {
    preferences.putString(BEST_USER_USER_NAME_KEY, user.getUserName());
    preferences.putInteger(BEST_USER_SCORE_KEY, user.getScore());
    preferences.flush();
  }

  public void setCurrentUser(String newUserName) {
    this.currentUser = retrieveUser(newUserName);
  }

  public User retrieveUser(String userName) {
    User user;
    if (!userName.equals(BEST_USER_USER_NAME_KEY)) {
      user = new RetroManiaGeneralUser(userName);
      user.setScore(preferences.getInteger(userName));
    } else {
      user = new RetroManiaGeneralUser(preferences.getString(userName));
      user.setScore(preferences.getInteger(BEST_USER_SCORE_KEY));
    }
    return user;
  }

  public void setScore(int score) {
    currentUser.setScore(score);
    saveNormalUser(currentUser);
    User bestUser = retrieveUser(BEST_USER_USER_NAME_KEY);
    currentUser = retrieveUser(currentUser.getUserName());
    if (bestUser.getScore() <= currentUser.getScore()) {
      saveBestUser(currentUser);
    }
  }
}
