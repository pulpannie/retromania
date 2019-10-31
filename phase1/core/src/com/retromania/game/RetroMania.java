package com.retromania.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.retromania.game.shared_abstractions.OrientationManager;
import com.retromania.game.shared_abstractions.RetroManiaGame;
//test commit
public class RetroMania extends RetroManiaGame {

	public RetroMania(OrientationManager orientationManager){
		super(orientationManager);
	}


	@Override
	public void render () {
		Gdx.gl.glClear(0);
		super.render();
	}

	@Override
	public void dispose () {
		sb.dispose();
		img.dispose();
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
