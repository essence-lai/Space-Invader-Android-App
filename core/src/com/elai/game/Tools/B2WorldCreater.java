package com.elai.game.Tools;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.utils.TiledDrawable;
import com.elai.game.KidInvader;
import com.elai.game.States.PlayState;

/**
 * Created by essen on 2017-01-08.
 */

public class B2WorldCreater {
    public B2WorldCreater(PlayState state){
        World world = state.getWorld();
        TiledMap map = state.getMap();

        BodyDef bdef = new BodyDef();
        PolygonShape shape = new PolygonShape();
        FixtureDef fdef = new FixtureDef();
        Body body;

        //for edges bodies/fixtures copy for other shit
        for(MapObject object : map.getLayers().get(2).getObjects().getByType(RectangleMapObject.class)){
            Rectangle rect = ((RectangleMapObject) object).getRectangle();

            bdef.type = BodyDef.BodyType.StaticBody;
            bdef.position.set((rect.getX()+rect.getWidth()/2)/ KidInvader.PPM,(rect.getY()+rect.getHeight()/2)/KidInvader.PPM);

            body = world.createBody(bdef);

            shape.setAsBox(rect.getWidth()/2/KidInvader.PPM, rect.getHeight()/2/KidInvader.PPM);
            fdef.shape = shape;
            body.createFixture(fdef);
        }
    }
}
