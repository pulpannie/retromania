package com.retromania.game;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.retromania.game.shared_abstractions.Creatable;
import com.retromania.game.shared_abstractions.RetroManiaGame;
import com.retromania.game.special_mario.SpecialMarioStarter;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		Creatable c = new Creatable<RetroMania>() {
			@Override
			public void create(RetroMania r) {
				r.sb = new SpriteBatch();
				r.setScreen(new GameLister());
//				r.setScreen(SpecialMarioStarter.getSpecialMarioStarter());
			}
		};
		RetroManiaGame game = RetroMania.getRetroManiaInstance().setCreatable(c);
		game.setOrientationManager(new OrientationManagerAndroid(this));
		initialize(game, config);
	}
}
