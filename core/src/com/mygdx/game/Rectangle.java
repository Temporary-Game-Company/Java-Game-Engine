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
    Vector2 size;
    float speed;
    float friction;
    BodyDef bodyDef = new BodyDef();
    Body rectangleBody;

    public Rectangle(Vector2 position, Vector2 size, float friction, boolean isStatic, World world) {
        this.position = new Vector2((position.x+size.x/2)/PPM, (position.y+size.y/2)/PPM);
        this.size = size.scl(1/PPM/2);
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
        rect.setAsBox(this.size.x, this.size.y);

        this.rectangleBody.createFixture(rect, 0f);
        this.rectangleBody.getFixtureList().get(0).setFriction(friction);
        rect.dispose();
    }
}
