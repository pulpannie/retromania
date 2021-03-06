package com.retromania.game.colour_shooter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.retromania.game.RetroMania;
import com.retromania.game.colour_shooter.states.GameStateManager;
import com.retromania.game.colour_shooter.states.MenuState;
import com.retromania.game.shared_abstractions.OrientationManager;
import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.shared_abstractions.RetroManiaGeneralUser;
import com.retromania.game.shared_abstractions.RetroManiaInnerGame;

import java.util.List;


public class ColourShooterStarter extends RetroManiaInnerGame {
	private GameStateManager gsm;
	private SpriteBatch batch;

	Texture img;

	public ColourShooterStarter(RetroManiaGame game) {
		super(game, "Colour Shooter", RetroManiaGame.Orientation.VERTICAL);
	}



	@Override
	public void show() {
		batch = new SpriteBatch();
		gsm = new GameStateManager();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		gsm.push(new MenuState(gsm));
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		gsm.update(Gdx.graphics.getDeltaTime());
		gsm.render(batch);
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void hide() {

	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	//TODO Override setCurrentUser : Which sets your user and you should use this for checking whether or not you have a personal best
	@Override
	public void setCurrentUser(String name) {
		this.currentUser = new RetroManiaGeneralUser(name);
	}
	//	TODO Override setBestUser : this is where you should try and retrieve information for your best user, look at save and retrieve functions
	@Override
	public void setBestUser() {
		this.bestUser = new RetroManiaGeneralUser("POR");
	}

	@Override
	public Integer getBestUserScore() {
		return null;
	}

	@Override
	public void save(Object... args) {

	}

	@Override
	public List<Object> retrieve() {
		return null;
	}

	@Override
	public void handleInput() {

	}
}
