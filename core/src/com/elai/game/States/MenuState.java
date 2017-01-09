package com.elai.game.States;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.elai.game.KidInvader;
import com.elai.game.Sprites.Kid;


/**
 * Created by essen on 2017-01-06.
 */

public class MenuState implements Screen {
    private Texture background;
    private Texture playButton;
    private Drawable drawable;
    private ImageButton playbt;
    private OrthographicCamera gameCam;
    private Viewport viewPort;
    private Stage stage;
    private Game game;


    public MenuState(Game game){

        this.game = game;
        //texture fields
        background = new Texture("background.png");
        playButton = new Texture("playbutton.png");
        drawable = new TextureRegionDrawable(new TextureRegion(playButton));

        playbt = new ImageButton(drawable);
        playbt.pack();



        //view port and gameCma
        gameCam = new OrthographicCamera(1,KidInvader.V_HEIGHT/KidInvader.V_WIDTH);
       /* gameCam.setToOrtho(false);
        gameCam.position.set(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2,0);
        gameCam.update();*/
        viewPort = new FitViewport(KidInvader.V_WIDTH,KidInvader.V_HEIGHT,gameCam);
        stage = new Stage(viewPort,((KidInvader)game).batch  );

        playbt.setPosition((viewPort.getWorldWidth()/2)-(playbt.getWidth()/2),(viewPort.getWorldHeight()/2)-(playbt.getHeight()/2));

        stage.addActor(playbt);
        Gdx.input.setInputProcessor(stage);

        //play button

    }



    public void handleInput() {
        if(Gdx.input.isTouched()){

            game.setScreen(new PlayState((KidInvader)game));
            dispose();
        }

    }

    public void update(float dt) {
        handleInput();
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float dt) {
        update(dt);

        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.getBatch().begin();
        stage.getBatch().draw(background, 0, 0, KidInvader.V_WIDTH,KidInvader.V_HEIGHT);
        stage.getBatch().end();

        stage.draw();
       /* game.batch.begin();
        game.batch.setProjectionMatrix(gameCam.combined);
        game.batch.draw(background,0,0,Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        game.batch.draw(playButton,(Gdx.graphics.getWidth()/2)-(playButton.getWidth()/2),(Gdx.graphics.getHeight()/2)-(playButton.getHeight()/2));
        game.batch.end();*/

    }

    @Override
    public void dispose() {
        background.dispose();
        playButton.dispose();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }
}
