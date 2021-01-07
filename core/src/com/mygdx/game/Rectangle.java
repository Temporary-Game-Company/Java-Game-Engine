package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import static com.mygdx.game.utils.Constants.PPM;

public class Rectangle {
    Vector2 position;
    float w;
    float h;
    float speed;
    float friction;
    BodyDef bodyDef = new BodyDef();
    Body rectangleBody;

    public Rectangle(Vector2 position, float w, float h, float friction, boolean isStatic, World world) {
        this.position = new Vector2((position.x+w/2)/PPM, (position.y+h/2)/PPM);
        this.w = w/PPM/2;
        this.h = h/PPM/2;
        this.speed = speed/PPM;
        this.friction = friction;


        if (isStatic)
            this.bodyDef.type = BodyDef.BodyType.StaticBody;
        else
            this.bodyDef.type = BodyDef.BodyType.DynamicBody;

        this.bodyDef.position.set(this.position);
        this.bodyDef.fixedRotation = true;

        this.rectangleBody = world.createBody(bodyDef);
        this.rectangleBody.setLinearDamping(1f);

        PolygonShape rect = new PolygonShape();
        rect.setAsBox(this.w, this.h);

        this.rectangleBody.createFixture(rect, 0f);
        this.rectangleBody.getFixtureList().get(0).setFriction(friction);
        rect.dispose();
    }
}
