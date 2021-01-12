package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import static com.mygdx.game.utils.Constants.PPM;

/**
 * Used to create a rectangle body with a fixture.
 */
public class Rectangle {
    /*Declarations.*/
    Vector2 position;
    Vector2 size;
    float speed;
    float friction;
    BodyDef bodyDef = new BodyDef();
    Body rectangleBody;

    public Rectangle(Vector2 position, Vector2 size, float friction, boolean isStatic, World world) {
        /*Initializes variables.*/
        this.position = new Vector2((position.x+size.x/2)/PPM, (position.y+size.y/2)/PPM);
        this.size = size.scl(1/PPM/2);
        this.speed = speed/PPM;
        this.friction = friction;

        /* Checks if static and sets BodyType accordingly.*/
        if (isStatic)
            this.bodyDef.type = BodyDef.BodyType.StaticBody;
        else
            this.bodyDef.type = BodyDef.BodyType.DynamicBody;

        /* Sets the position of bodyDef and fixes rotation value.*/
        this.bodyDef.position.set(this.position);
        this.bodyDef.fixedRotation = true;

        /* Creates body and sets linear damping to 1.*/
        this.rectangleBody = world.createBody(bodyDef);
        this.rectangleBody.setLinearDamping(1f);  // Force which slows an object to a halt.

        /* Declares/Initializes a polygon and sets it's size.*/
        PolygonShape rect = new PolygonShape();
        rect.setAsBox(this.size.x, this.size.y);

       /*  Creates a fixture for the rectangle and sets it's friction.*/
        this.rectangleBody.createFixture(rect, 0f);
        this.rectangleBody.getFixtureList().get(0).setFriction(friction);

        rect.dispose();  /* Disposes of shape.*/
    }
}
