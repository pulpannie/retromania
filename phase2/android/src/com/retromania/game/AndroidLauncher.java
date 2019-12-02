package com.retromania.game;

import android.os.Build;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.retromania.game.shared_abstractions.Creatable;
import com.retromania.game.shared_abstractions.RetroManiaGame;

import static com.retromania.game.mario.SpecialMarioConfiguration.FINAL_GAME;


@android.annotation.TargetApi(Build.VERSION_CODES.N)
public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		boolean skipToMario = false;
    Creatable c =
			(Creatable<RetroMania>) r -> {
			  r.sb = new SpriteBatch();
			  r.setScreen(new GameLister());
			  if (skipToMario)
              r.setScreen(FINAL_GAME);
			};
		RetroManiaGame game = RetroMania.getRetroManiaInstance().setCreatable(c);
		game.setOrientationManager(new OrientationManagerAndroid(this));
		initialize(game, config);
	}
}
