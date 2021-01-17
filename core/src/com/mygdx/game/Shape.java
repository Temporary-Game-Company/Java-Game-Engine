package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

import static com.mygdx.game.utils.Constants.PPM;

public class Shape {
    /*Declarations.*/
    protected Vector2 position;
    protected Vector2 size;
    protected float friction;
    protected BodyDef bodyDef = new BodyDef();
    protected Body body;
    protected FixtureDef fixtureDef;

    public Shape(Vector2 position, Vector2 size, float friction, float damping, boolean isStatic, World world, short cBits, short mBits) {
        /*Initializes variables.*/
        this.position = new Vector2((position.x + size.x / 2) / PPM, (position.y + size.y / 2) / PPM);
        this.size = size.scl(1 / PPM / 2);
        this.friction = friction;

        /* Checks if static and sets BodyType accordingly.*/
        if (isStatic)
            this.bodyDef.type = BodyDef.BodyType.StaticBody;
        else
            this.bodyDef.type = BodyDef.BodyType.DynamicBody;

        /* Sets the position of bodyDef and fixes rotation value.*/
        this.bodyDef.position.set(this.position);
        this.bodyDef.fixedRotation = true;

        this.body = world.createBody(bodyDef);
        this.body.setLinearDamping(damping);  // Force which slows an object to a halt.

        fixtureDef = new FixtureDef();
        fixtureDef.friction = friction;
        fixtureDef.filter.categoryBits = cBits;
        fixtureDef.filter.maskBits = mBits;

    }
}
