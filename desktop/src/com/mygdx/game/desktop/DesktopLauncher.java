package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Jebja";
		config.width = 1920;
		config.height = 1080;
		config.fullscreen = true;
		config.backgroundFPS = 60;
		config.foregroundFPS = 60;

		new LwjglApplication(new Main(), config);
	}
}