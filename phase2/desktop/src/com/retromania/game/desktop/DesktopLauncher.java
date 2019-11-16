package com.retromania.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.retromania.game.GameLister;
import com.retromania.game.RetroMania;
import com.retromania.game.shared_abstractions.RetroManiaGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		RetroManiaGame game = new RetroMania(new OrientationManagerPC()) {
			@Override
			public void create() {
				sb = new SpriteBatch();
				setScreen(new GameLister(this));
			}
		};
		new LwjglApplication(game, config);
	}
}
