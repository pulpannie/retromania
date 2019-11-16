package com.retromania.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.retromania.game.shared_abstractions.RetroManiaGame;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		RetroManiaGame game = new RetroMania(new OrientationManagerAndroid(this)) {
			@Override
			public void create() {
				sb = new SpriteBatch();
				setScreen(new GameLister(this));
			}
		};
		initialize(game, config);
	}
}
