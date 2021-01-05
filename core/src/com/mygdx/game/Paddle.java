package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.mygdx.game.utils.Constants.PPM;

public class Paddle {
    float x;
    float y;
    float w;
    float h;
    BodyDef bodyDef = new BodyDef();
    Body paddleBody;

    public Paddle(float x, float y, float w, float h, boolean isStatic, World world) {
        this.x = x/PPM;
        this.y = y/PPM;
        this.w = w/PPM;
        this.h = h/PPM;

        if (isStatic)
            bodyDef.type = BodyDef.BodyType.StaticBody;
        else
            bodyDef.type = BodyDef.BodyType.DynamicBody;

        bodyDef.position.set(x, y);
        bodyDef.fixedRotation = true;
        paddleBody = world.createBody(bodyDef);

        PolygonShape rect = new PolygonShape();
        rect.setAsBox(this.w, this.h);

        paddleBody.createFixture(rect, 0f);
        rect.dispose();
    }

}
