package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.World;

public class Ball {
    int x;
    int y;
    int size;
    int xSpeed;
    int ySpeed;
    Color color = Color.WHITE;
    BodyDef ballDef = new BodyDef();

    public Ball(int x, int y, int size, int xSpeed, int ySpeed, World world) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        ballDef.type = BodyType.DynamicBody;
        ballDef.position.set(x, y);
        Body ballBody = world.createBody(ballDef);
        CircleShape ball = new CircleShape();
        ball.setRadius(size);
    }

    public void update() {
        this.x += xSpeed;
        this.y += ySpeed;
        if (this.x < this.size || this.x > Gdx.graphics.getWidth() - this.size) {
            xSpeed = -xSpeed;
        }
        if (this.y < this.size || this.y > Gdx.graphics.getHeight() - this.size) {
            ySpeed = -ySpeed;
        }
    }

    public void draw(ShapeRenderer shape) {
        shape.setColor(color);
        ballDef.position.set(x, y);
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
