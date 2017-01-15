package com.elai.game.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.elai.game.KidInvader;

/**
 * Created by essen on 2017-01-05.
 */

public class Hud implements Disposable{
    public Stage stage;
    private Viewport viewport;
    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;




    private static Integer score;

    private static Label scoreLabel;


    public Hud (SpriteBatch sb){
        score = 0;

        generator = new FreeTypeFontGenerator(Gdx.files.internal("Arcadepix Plus.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 225;

        viewport = new FitViewport(KidInvader.V_WIDTH,KidInvader.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        BitmapFont font = generator.generateFont(parameter); // font size 12 pixels

        Table table = new Table();
        table.top();
        table.padTop(6f);
        table.setFillParent(true);

        scoreLabel =  new Label(String.format("%d", score), new Label.LabelStyle(font, Color.DARK_GRAY));

        table.add(scoreLabel).expandX();

        stage.addActor(table);
    }

    @Override
    public void dispose() {
        stage.dispose();
        generator.dispose();
    }

    public static void addScore(int value){
        score+= value;
        scoreLabel.setText(String.format("%d", score));
    }
}
