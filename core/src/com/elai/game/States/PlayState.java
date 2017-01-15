package com.elai.game.States;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.elai.game.KidInvader;
import com.elai.game.Scenes.Hud;
import com.elai.game.Sprites.Kid;
import com.elai.game.Sprites.Obstacle;
import com.elai.game.Tools.B2WorldCreater;
import com.elai.game.Tools.WorldContactListener;

/**
 * Created by essen on 2017-01-07.
 */

public class PlayState implements Screen{
    private Texture texture;
    private OrthographicCamera gameCam;
    private Viewport gamePort;
    private KidInvader game;


    //Map loader
    private TmxMapLoader mapLoader;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    //HUd
    private Hud hud;

    //Box2d
    private World world;
    private Box2DDebugRenderer b2dr;

    //Sprites
    private Kid kid;
    private Obstacle obstacle;

    public PlayState( KidInvader game) {
        this.game = game;
        gameCam = new OrthographicCamera();
        gamePort = new FitViewport(KidInvader.M_WIDTH/KidInvader.PPM,KidInvader.M_HEIGHT/KidInvader.PPM,gameCam);

        //texture = new Texture("kid.png");
        //create hud
        hud = new Hud(game.batch);

        //load map
        mapLoader = new TmxMapLoader();
        map = mapLoader.load("kidMap.tmx");
        renderer = new OrthogonalTiledMapRenderer(map,1/KidInvader.PPM);


        //initiall get game position
        //gameCam.setToOrtho(false, gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2);
         gameCam.position.set(gamePort.getWorldWidth()/2,gamePort.getWorldHeight()/2,0);



       // kid = new Kid(KidInvader.V_WIDTH/2-16,40);

        world = new World(new Vector2(0,0), true);
        b2dr = new Box2DDebugRenderer();

        new B2WorldCreater(this);

        //create kid
        kid = new Kid(this);

        //create obstacles
        obstacle = new Obstacle(this, 500);


        //world contact listener
        world.setContactListener( new WorldContactListener());


    }

    public World getWorld(){
        return world;
    }
    public TiledMap getMap(){return map; }
    public void handleInput(float dt) {

        if(Gdx.input.isTouched()) {
            //gameCam.position.y += 100 * dt;
            if((Gdx.input.getX() <= (Gdx.graphics.getWidth()/2))&& kid.b2body.getLinearVelocity().x >= -2){
                    kid.b2body.applyLinearImpulse(new Vector2(-2f,0),kid.b2body.getWorldCenter(),true);
            }else if ((Gdx.input.getX() > (Gdx.graphics.getWidth()/2))&& kid.b2body.getLinearVelocity().x  <= 2) {
                kid.b2body.applyLinearImpulse(new Vector2(2f,0),kid.b2body.getWorldCenter(),true);
            }
        }

    }


    public void update(float dt) {
        //kid.b2body.setLinearVelocity(0,1f);
        //gameCam.position.y = kid.b2body.getPosition().y+gameCam.viewportHeight/2 - kid.getHeight();
        handleInput(dt);
        kid.update(dt);
        world.step(1/60f, 6, 2);
        //gameCam.position.x = kid.b2body.getPosition().x;
        gameCam.update();
        renderer.setView(gameCam);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float dt) {
        update(dt);

        //clear screen to white
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //render map
        renderer.setView(gameCam);
        renderer.render();

        //render our box 2ddebuglines
        b2dr.render(world,gameCam.combined);


        game.batch.setProjectionMatrix(gameCam.combined);
        //gameCam.update();
        //renderer.setView(gameCam);

        game.batch.begin();
        //game.batch.setProjectionMatrix(gameCam.combined);
        kid.draw(game.batch);
        obstacle.draw(game.batch);
        //game.batch.draw(texture,0,0);
        game.batch.end();
        //draw hud
        game.batch.setProjectionMatrix(hud.stage.getCamera().combined);
        hud.stage.draw();

    }

    @Override
    public void dispose() {
        map.dispose();
        renderer.dispose();
        world.dispose();
        b2dr.dispose();
        hud.dispose();
    }

    @Override
    public void resize(int width, int height) {
         gamePort.update(width,height);

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
