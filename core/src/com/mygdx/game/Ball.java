package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Ball {
    int x;
    int y;
    int size;
    int xSpeed;
    int ySpeed;
    Color color = Color.WHITE;
    BodyDef bodyDef = new BodyDef();
    Body ballBody;

    public Ball(int x, int y, int size, int xSpeed, int ySpeed, World world) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;

        bodyDef.type = BodyType.DynamicBody;
        bodyDef.position.set(x, y);

        this.ballBody = world.createBody(bodyDef);
        ballBody.setLinearDamping(8f);

        CircleShape ball = new CircleShape();
        ball.setRadius(size);
        fixBall(ball, ballBody);
        ball.dispose();

    }

    public void fixBall(CircleShape ball, Body ballBody) {
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = ball;

        Fixture fixture = ballBody.createFixture(fixtureDef);

    }

    public void move() {
        int speed = 200;
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

    public void draw(ShapeRenderer shape) {
        shape.setColor(color);
    }

    public void checkCollision(Paddle paddle) {
        if(collidesWith(paddle)) {
            color = Color.GREEN;
        } else {
            color = Color.WHITE;
        }
    }

    private boolean collidesWith(Paddle paddle) {
        return Math.random()>.5;
    }
}
