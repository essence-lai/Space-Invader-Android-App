package com.elai.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.particles.influencers.ColorInfluencer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.elai.game.KidInvader;
import com.elai.game.States.PlayState;

import java.util.Random;

/**
 * Created by essen on 2017-01-14.
 */


public class Obstacle extends Sprite {
    protected World world;
    protected PlayState state;
    public Body leftBody;
    public Body rightBody;

    private static  final int FLUNCTUATION  = 50;
    private static  final int GAP = 40;
    private static  final int LOWEST_OPENING = 50;
    private Texture leftObstacle;
    private Texture rightObstacle;
    private TextureRegion leftObj;
    private TextureRegion rightObj;
    private Random rand;



    public Obstacle(PlayState state, float y){
        this.world  = state.getWorld();
        this.state = state;
        setPosition(KidInvader.M_WIDTH/KidInvader.PPM,y);

        leftObstacle = new Texture("obstacle.png");
        rightObstacle = new Texture("obstacle.png");

        leftObj = new TextureRegion(leftObstacle,0,0,208,57);
        rightObj= new TextureRegion(rightObstacle,0,0,208,57);

        rand = new Random();

        setBounds(0,0,85/KidInvader.PPM, 31/KidInvader.PPM);
        setRegion(leftObj);
        setRegion(rightObj);

        defineObstacle(y);



    }

    public void defineObstacle(float y){
        BodyDef lbdef = new BodyDef();
        BodyDef rbdef = new BodyDef();

        lbdef.position.set((rand.nextInt(FLUNCTUATION) + GAP + LOWEST_OPENING)/KidInvader.PPM,y);
        lbdef.type = BodyDef.BodyType.DynamicBody;
        leftBody = world.createBody(lbdef);

        rbdef.position.set((lbdef.position.x - GAP -rightObj.getRegionWidth())/KidInvader.PPM,y);
        rbdef.type = BodyDef.BodyType.DynamicBody;
        rightBody = world.createBody(rbdef);

        FixtureDef lfdef = new FixtureDef();
        FixtureDef rfdef = new FixtureDef();

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(85,57);


        lfdef.shape = shape;
        rfdef.shape = shape;
        leftBody.createFixture(lfdef);
        rightBody.createFixture(rfdef);
    }

    public Body getLeftBody() {
        return leftBody;
    }

    public Body getRightBody() {
        return rightBody;
    }

    public TextureRegion getLeftObj() {
        return leftObj;
    }

    public TextureRegion getRightObj() {
        return rightObj;
    }

    public void draw(Batch batch){
        super.draw(batch);
    }
}
