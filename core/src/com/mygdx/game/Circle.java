package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import static com.mygdx.game.utils.Constants.PPM;

/**
 * Used to create a circle body with a fixture.
 */
public class Circle {
    /* Declarations.*/
    Vector2 position;
    float size;
    float speed;
    BodyDef bodyDef = new BodyDef();
    Body circleBody;

    public Circle(Vector2 position, float size, float friction, boolean isStatic, World world) {
        /*Initializes variables.*/
        this.position = position.scl(1/PPM);
        this.size = size/PPM;
        this.speed = speed/PPM;

        /* Checks if static and sets BodyType accordingly.*/
        if (isStatic)
            this.bodyDef.type = BodyType.StaticBody;
        else
            this.bodyDef.type = BodyType.DynamicBody;

        /*Sets the position of bodyDef and fixes rotation value.*/
        this.bodyDef.position.set(this.position);
        this.bodyDef.fixedRotation = true;

        /* Creates body and sets linear damping to 1.*/
        this.circleBody = world.createBody(bodyDef);
        this.circleBody.setLinearDamping(1f);  /* Force which slows an object to a halt.*/

        /* Declares/Initializes a circle and sets it's radius.*/
        CircleShape circle = new CircleShape();
        circle.setRadius(this.size);

        /* Creates a fixture for the rectangle and sets it's friction.*/
        this.circleBody.createFixture(circle, 0f);
        this.circleBody.getFixtureList().get(0).setFriction(friction);

        circle.dispose();  /* Disposes of shape.*/
    }
}
