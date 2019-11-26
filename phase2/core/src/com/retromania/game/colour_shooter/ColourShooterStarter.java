package com.retromania.game.colour_shooter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.retromania.game.RetroMania;
import com.retromania.game.colour_shooter.individuals.ColourShootGameStats;
import com.retromania.game.colour_shooter.screens.MainScreenInterface;
import com.retromania.game.colour_shooter.screens.MenuScreen;
import com.retromania.game.colour_shooter.screens.PauseScreen;
import com.retromania.game.colour_shooter.screens.PlayScreen;
import com.retromania.game.colour_shooter.screens.StateFactory;
import com.retromania.game.colour_shooter.states.GameStateManager;
import com.retromania.game.colour_shooter.states.MenuState;
import com.retromania.game.shared_abstractions.Configuration;
import com.retromania.game.shared_abstractions.OrientationManager;
import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.shared_abstractions.RetroManiaGeneralUser;
import com.retromania.game.shared_abstractions.RetroManiaInnerGame;
import com.retromania.game.shared_abstractions.User;
import com.retromania.game.spaceship_shooter.individuals.GameStats;

import java.util.List;


public class ColourShooterStarter extends RetroManiaInnerGame implements MainScreenInterface {

	static ColourShootGameStats gameStats;
	static Screen pauseScreen;
	static Screen playScreen;
	static Screen menuScreen;
	private Preferences preferences;
	public int tankPrefrence;

	public ColourShooterStarter(RetroManiaGame game) {
		super(game, "Colour Shooter", RetroManiaGame.Orientation.VERTICAL);
		gameStats = new ColourShootGameStats();
		playScreen = StateFactory.getScreen("play screen", game, this);
		pauseScreen = StateFactory.getScreen("pause screen", game, this);
		menuScreen = StateFactory.getScreen("menu screen", game, this);
		preferences = game.getPrefrences(Configuration.colourshooterPreference);
		tankPrefrence = 1;
	}

	public static ColourShootGameStats getGameStats() {
		return gameStats;
	}

	public void changeTankPreference(int tankPref) {
		tankPrefrence = tankPref;
	}

	public int getTankPreference() {
		return tankPrefrence;
	}

	public static Screen getPlayScreen() {
		return playScreen;
	}

	public static Screen getPauseScreen() {
		return pauseScreen;
	}

	public static Screen getMenuScreen() {
		return menuScreen;
	}

	public static void setGameStats(ColourShootGameStats gameStats) {
		ColourShooterStarter.gameStats = gameStats;
	}

	public static void setPlayScreen(PlayScreen playScreen) {
		ColourShooterStarter.playScreen = playScreen;
	}

	public static void setPauseScreen(PauseScreen pauseScreen) {
		ColourShooterStarter.pauseScreen = pauseScreen;
	}

	public static void setMenuScreen(MenuScreen menuScreen) {
		ColourShooterStarter.menuScreen = menuScreen;
	}

	@Override
	public void setCurrentUser(String name) {
		this.currentUser = new RetroManiaGeneralUser(name);
		if (preferences.contains(currentUser.getUserName())){
			this.currentUser.setScore(preferences.getInteger(currentUser.getUserName()));
		}
	}
	@Override
	public void setBestUser() {
		preferences = game.getPrefrences(Configuration.colourshooterPreference);
		if (preferences.contains("best player name")){
			this.bestUser = new RetroManiaGeneralUser(preferences.getString("best player name"));
			this.bestUser.setScore(preferences.getInteger(bestUser.getUserName()));
		}
		else{
			this.bestUser = new RetroManiaGeneralUser("");
		}
	}

	@Override
	public Integer getBestUserScore() {
		return bestUser.getScore();
	}

	public void save(Object... args) {
		if(!preferences.contains("best player name") || currentUser.getScore() > bestUser.getScore()){
			preferences.putString("best player name", this.currentUser.getUserName());
			preferences.putInteger(this.currentUser.getUserName(), this.currentUser.getScore());
		}
		else if (!preferences.contains(currentUser.getUserName()) || currentUser.getScore() > preferences.getInteger(currentUser.getUserName())){
			preferences.putInteger(this.currentUser.getUserName(), this.currentUser.getScore());
		}

		preferences.flush();

	}

	@Override
	public List<Object> retrieve() { return null; }

	@Override
	public void handleInput() {

	}

	@Override
	public void show() {
		game.setScreen(menuScreen);
	}

	@Override
	public void render(float delta) {

	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {
		//game.setScreen(pauseScreen);
	}

	@Override
	public void resume() {
		//game.setScreen(playScreen);
	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose() {

	}

	public void restart(){
//		setPlayScreen(new PlayScreen(game, this));
//		game.setScreen(getPlayScreen());
	}

	public User getUser(){return currentUser;}

}
