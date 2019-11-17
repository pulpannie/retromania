package com.retromania.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.retromania.game.GameLister;
import com.retromania.game.RetroMania;
import com.retromania.game.shared_abstractions.Creatable;
import com.retromania.game.shared_abstractions.RetroManiaGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();


		Creatable c = new Creatable<RetroMania>() {
			@Override
			public void create(RetroMania r) {
				r.sb = new SpriteBatch();
				r.setScreen(new GameLister());
			}
		};
		RetroManiaGame game = RetroMania.getRetroManiaInstance().setCreatable(c);
		game.setOrientationManager(new OrientationManagerPC());
		new LwjglApplication(game, config);
	}
}
