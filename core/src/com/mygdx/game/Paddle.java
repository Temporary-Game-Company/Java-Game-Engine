package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import static com.badlogic.gdx.graphics.Color.WHITE;

public class Paddle {
    int x;
    int y;

    public Paddle(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        this.x = Gdx.input.getX();
        this.y = Gdx.graphics.getHeight() - Gdx.input.getY();
    }

    public void draw(ShapeRenderer shape) {
        shape.setColor(WHITE);
        shape.rect(this.x-25, this.y-5, 50, 10);
    }
}
