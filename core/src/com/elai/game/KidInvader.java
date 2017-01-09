package com.elai.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.elai.game.States.MenuState;

public class KidInvader extends Game implements ApplicationListener {
	public static final int V_WIDTH = 480;
	public static final int V_HEIGHT = 800;
	public static final int M_WIDTH = 208;
	public static final int M_HEIGHT = 400;
	public static final float PPM = 100;
	public static final String TITLE = "KID INVADER";
	public SpriteBatch batch;
	public static AssetManager manager;


	@Override
	public void create () {
		batch = new SpriteBatch();
		manager = new AssetManager();
		//loud sounds effects for later in here
		manager.finishLoading();

		setScreen(new MenuState(this));
	}

	@Override
	public void dispose(){
		super.dispose();
		manager.dispose();
		batch.dispose();
	}
	@Override
	public void render () {
		super.render();
	}

}
