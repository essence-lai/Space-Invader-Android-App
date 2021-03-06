package com.elai.game.Sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.EdgeShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.elai.game.KidInvader;
import com.elai.game.States.PlayState;
import com.elai.game.Tools.WorldContactListener;

/**
 * Created by essen on 2017-01-07.
 */

public class Kid extends Sprite{
    public enum State{STANDING,DEAD};
    public State currentState;
    public State previousState;


    public World world;
    public Body b2body;

    private Vector3 position;
    private Vector3 velocity;

    private Texture kid;
    private TextureRegion kidR;

    private PlayState state;
    private Boolean kidIsDead;

    public Kid(PlayState state){
        this.state = state;
        this.world = state.getWorld();

        kid = new Texture("kid.png");
        kidR = new TextureRegion(kid, 0 , 0 , 33, 47);
        defineKid();

        //set initial vaules of kid location
        setBounds(0,0,22/KidInvader.PPM, 31/KidInvader.PPM);
        setRegion(kidR);

    }

    public void defineKid(){
        BodyDef bdef = new BodyDef();
        bdef.position.set(KidInvader.M_WIDTH/2/KidInvader.PPM,37/KidInvader.PPM);
        //bdef.linearVelocity.set(0,0.1f);
        bdef.type = BodyDef.BodyType.DynamicBody;
        b2body = world.createBody(bdef);

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(7/KidInvader.PPM);


        fdef.shape = shape;
        b2body.createFixture(fdef);

        EdgeShape leftSide = new EdgeShape();
        leftSide.set(new Vector2(-7/KidInvader.PPM,7/KidInvader.PPM),new Vector2(-7/KidInvader.PPM,-7/KidInvader.PPM));
        fdef.shape = leftSide;
        fdef.isSensor = true;
        b2body.createFixture(fdef).setUserData("leftSide");

        EdgeShape rightSide = new EdgeShape();
        leftSide.set(new Vector2(7/KidInvader.PPM,7/KidInvader.PPM),new Vector2(7/KidInvader.PPM,-7/KidInvader.PPM));
        fdef.shape = rightSide;
        fdef.isSensor = true;
        b2body.createFixture(fdef).setUserData("rightSide");


    }

    public void update(float dt){


        setPosition(b2body.getPosition().x-getWidth()/2,b2body.getPosition().y-getHeight()/2);
    }

    public State getState(){
        if(kidIsDead)
            return State.DEAD;
        return State.STANDING;
    }
    public void draw(Batch batch){
        super.draw(batch);
    }

    public void die(){
        if(!isDead()){
            kidIsDead = true;

        }
    }
    public boolean isDead(){
        return kidIsDead;
    }

}
