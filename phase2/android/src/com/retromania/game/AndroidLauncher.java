package com.retromania.game;

import android.os.Build;
import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.retromania.game.shared_abstractions.Creatable;
import com.retromania.game.shared_abstractions.RetroManiaGame;

import static com.retromania.game.special_mario.SpecialMarioConfiguration.FINAL_GAME;

@android.annotation.TargetApi(Build.VERSION_CODES.N)
public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
    Creatable c =
        new Creatable<RetroMania>() {
          @Override
          public void create(RetroMania r) {
            r.sb = new SpriteBatch();
            r.setScreen(new GameLister());
//            r.setScreen(FINAL_GAME);
//			  r.setOrientation(TicTacToeConfiguration.FINAL_GAME.getOrientation());
//			  r.setScreen(TicTacToeConfiguration.FINAL_GAME);
          }
        };
		RetroManiaGame game = RetroMania.getRetroManiaInstance().setCreatable(c);
		game.setOrientationManager(new OrientationManagerAndroid(this));
		initialize(game, config);
	}
}
