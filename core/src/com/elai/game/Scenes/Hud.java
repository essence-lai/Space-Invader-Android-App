package com.elai.game.Scenes;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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

    private Integer worldTimer;
    private float timeCount;
    private Integer score;

    Label countdownLabel;
    Label scoreLabel;
    Label timeLabel;
    Label levelLabel;
    Label kidLabel;
    Label worldLabel;

    public Hud (SpriteBatch sb){
        worldTimer = 300;
        timeCount = 0;
        score = 0;

        viewport = new FitViewport(KidInvader.V_WIDTH,KidInvader.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb);

        Table table = new Table();
        table.top();
        table.setFillParent(true);

        countdownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.DARK_GRAY));
        scoreLabel =  new Label(String.format("%06d", score), new Label.LabelStyle(new BitmapFont(), Color.DARK_GRAY));
        timeLabel =  new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.DARK_GRAY));
        worldLabel = new Label("LEVEL", new Label.LabelStyle(new BitmapFont(),Color.DARK_GRAY));
        levelLabel =  new Label("1-1", new Label.LabelStyle(new BitmapFont(), Color.DARK_GRAY));
        kidLabel = new Label("CHRISTOPHER!", new Label.LabelStyle(new BitmapFont(), Color.DARK_GRAY));

        table.add(kidLabel).expandX().padTop(10);
        table.add(worldLabel).expandX().padTop(10);
        table.add(timeLabel).expandX().padTop(10);
        table.row();
        table.add(scoreLabel).expandX();
        table.add(levelLabel).expandX();
        table.add(countdownLabel).expandX();

        stage.addActor(table);
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
