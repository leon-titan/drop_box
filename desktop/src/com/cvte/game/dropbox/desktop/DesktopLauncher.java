package com.cvte.game.dropbox.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.cvte.game.dropbox.Box2dLightTest;
import com.cvte.game.dropbox.BoxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = 720;
        config.height = 1280;
		new LwjglApplication(new BoxGame(), config);
	}
}
