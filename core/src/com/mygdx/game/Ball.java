package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import static com.mygdx.game.utils.Constants.PPM;

public class Ball {
    int x;
    int y;
    float size;
    Color color = Color.WHITE;
    BodyDef bodyDef = new BodyDef();
    Body ballBody;

    public Ball(int x, int y, float size, boolean isStatic, World world) {
        this.x = x;
        this.y = y;
        this.size = size/PPM;

        if (isStatic)
            bodyDef.type = BodyType.StaticBody;
        else
            bodyDef.type = BodyType.DynamicBody;

        bodyDef.position.set(x, y);
        bodyDef.fixedRotation = true;

        this.ballBody = world.createBody(bodyDef);
        ballBody.setLinearDamping(5f);

        CircleShape ball = new CircleShape();
        ball.setRadius(this.size);

        ballBody.createFixture(ball, 0f);
        ball.dispose();

    }


    public void move() {
        int speed = 1;
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            this.ballBody.applyLinearImpulse(speed, 0, this.x, this.y, true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            this.ballBody.applyLinearImpulse(-speed, 0, this.x, this.y, true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            this.ballBody.applyLinearImpulse(0, speed, this.x, this.y, true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            this.ballBody.applyLinearImpulse(0, -speed, this.x, this.y, true);
        }
    }
}
