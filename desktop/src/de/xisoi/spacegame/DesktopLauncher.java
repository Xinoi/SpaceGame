package de.xisoi.spacegame;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setTitle("Space Game");
		config.setForegroundFPS(60);
		config.setWindowedMode(1200, 800);
		config.useVsync(true);
		new Lwjgl3Application(new SpaceGame(), config);
	}
}
