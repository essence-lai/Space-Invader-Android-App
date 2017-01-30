package com.elai.game.States;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.elai.game.KidInvader;
import com.elai.game.Scenes.Hud;

/**
 * Created by essen on 2017-01-14.
 */

public class GameOverState implements Screen {
    private Viewport viewport;
    private Stage stage;
    private Game game;
    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter2;




    public GameOverState(Game game){
        this.game = game;
        viewport = new FitViewport(KidInvader.V_WIDTH,KidInvader.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((KidInvader)game).batch);

        generator = new FreeTypeFontGenerator(Gdx.files.internal("Arcadepix Plus.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter2 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 100;
        parameter2.size = 40;

        BitmapFont font = generator.generateFont(parameter);
        BitmapFont font2 = generator.generateFont(parameter2);

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Label gameOverLabel = new Label("GAME OVER",new Label.LabelStyle(font, Color.WHITE));
        Label playAgainLabel = new Label("Click to Play Again",new Label.LabelStyle(font2, Color.WHITE));

        table.add(gameOverLabel);
        table.row();
        table.add(Hud.scoreLabel).expandX().padTop(10f);
        table.row();
        table.add(playAgainLabel).expandX().padTop(10f);

        stage.addActor(table);
    }

    @Override
    public void show() {

    }

    public void handleInput() {

        if(Gdx.input.justTouched()){
            game.setScreen(new MenuState(game));
            dispose();
        }

    }

    public void update(float dt) {
        handleInput();
    }


    @Override
    public void render(float delta) {

        update(delta);
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.draw();

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

    @Override
    public void dispose() {
        stage.dispose();
        generator.dispose();
    }
}
