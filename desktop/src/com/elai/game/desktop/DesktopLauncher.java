package com.elai.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.elai.game.KidInvader;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = KidInvader.V_WIDTH;
		config.height = KidInvader.V_HEIGHT;
		config.title = KidInvader.TITLE;
		new LwjglApplication(new KidInvader(), config);
	}
}
