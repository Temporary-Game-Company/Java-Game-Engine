package com.mygdx.game;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;

import static com.mygdx.game.utils.Constants.PPM;

/**
 * Used to create a rectangle body with a fixture.
 */
public class Rectangle extends Shape{

    public Rectangle(Vector2 position, Vector2 size, float friction, float damping, boolean isStatic, World world, short cBits, short mBits) {
        super(position,size,friction,damping,isStatic,world,cBits,mBits);

        /* Declares/Initializes a polygon and sets it's size.*/
        PolygonShape rect = new PolygonShape();
        rect.setAsBox(super.size.x, super.size.y);

       /*  Creates a fixture for the rectangle and sets it's friction.*/
        super.fixtureDef.shape = rect;

        super.body.createFixture(fixtureDef);

        rect.dispose();  /* Disposes of shape.*/
    }
}
