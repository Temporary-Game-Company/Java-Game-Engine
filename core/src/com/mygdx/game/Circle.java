package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import static com.mygdx.game.utils.Constants.PPM;

public class Circle {
    Vector2 position;
    float size;
    float speed;
    BodyDef bodyDef = new BodyDef();
    Body circleBody;

    public Circle(Vector2 position, float size, boolean isStatic, World world) {
        this.position = position.scl(1/PPM);
        this.size = size/PPM;
        this.speed = speed/PPM;

        if (isStatic)
            this.bodyDef.type = BodyType.StaticBody;
        else
            this.bodyDef.type = BodyType.DynamicBody;

        this.bodyDef.position.set(this.position);
        this.bodyDef.fixedRotation = true;

        this.circleBody = world.createBody(bodyDef);
        this.circleBody.setLinearDamping(1f);

        CircleShape circle = new CircleShape();
        circle.setRadius(this.size);


        this.circleBody.createFixture(circle, 0f);
        circle.dispose();

    }
}
