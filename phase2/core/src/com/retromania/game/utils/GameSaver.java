package com.retromania.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.retromania.game.shared_abstractions.RetroManiaGeneralUser;
import com.retromania.game.shared_abstractions.User;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;


public class GameSaver {

  public User getCurrentUser() {
    return currentUsers.get(nameOfGame);
  }
  private void setCurrentUser(User user){
    currentUsers.put(nameOfGame, user);
  }

  private static final Map<String, Preferences> preferences;
  private static final Map<String, User> currentUsers;
  static {
    preferences = new HashMap<>();
    currentUsers = new HashMap<>();
  }



//  private User currentUser;
//  private Preferences preferences;
  public static final String BEST_USER_USER_NAME_KEY = "$BEST_USER$NAME$";
  private static final String BEST_USER_SCORE_KEY = "$BEST_USER$SCORE$";

  private final String nameOfGame;
  private Preferences preference;


  public GameSaver(String nameOfGame, String nameOfUser) {
    this.nameOfGame = nameOfGame;
    setUpPreference();
    setCurrentUser(nameOfUser);
  }

  private void setUpPreference(){
    if(!preferences.keySet().contains(nameOfGame))
      preferences.put(nameOfGame, getPrefrences(nameOfGame));
    preference = preferences.get(nameOfGame);
  }

  @Inject
  public GameSaver(@Named("name Of Game") String nameOfGame) {
    this(nameOfGame, "");
  }

  public void saveNormalUser(User user) {
    preference.putInteger(user.getUserName(), user.getScore());
    preference.flush();
  }

  public void saveBestUser(User user) {
    preference.putString(BEST_USER_USER_NAME_KEY, user.getUserName());
    preference.putInteger(BEST_USER_SCORE_KEY, user.getScore());
    preference.flush();
  }

  public void setCurrentUser(String newUserName) {
    currentUsers.put(nameOfGame, retrieveUser(newUserName));
  }

  public User retrieveUser(String userName) {
    User user;
    if (!userName.equals(BEST_USER_USER_NAME_KEY)) {
      user = new RetroManiaGeneralUser(userName);
      user.setScore(preference.getInteger(userName));
    } else {
      user = new RetroManiaGeneralUser(preference.getString(userName));
      user.setScore(preference.getInteger(BEST_USER_SCORE_KEY));
    }
    return user;
  }

  public void setScore(int score) {
    getCurrentUser().setScore(score);
    saveNormalUser(getCurrentUser());
    User bestUser = retrieveUser(BEST_USER_USER_NAME_KEY);
    setCurrentUser(retrieveUser(getCurrentUser().getUserName()));
    if (bestUser.getScore() <= getCurrentUser().getScore()) {
      saveBestUser(getCurrentUser());
    }
  }

  static private Preferences getPrefrences(String name){
    return Gdx.app.getPreferences(name);
  }
}
