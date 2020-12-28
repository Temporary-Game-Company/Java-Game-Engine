package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Matrix4;

import java.util.ArrayList;
import java.util.Random;

public class TheGame extends ApplicationAdapter {
    ShapeRenderer shape;  // Used to render white shapes.
    ArrayList<Ball> balls = new ArrayList<>();
    Paddle paddle;
    Random r = new Random();
    int randomSize;
    World world = new World(new Vector2(0,0), true);
    private OrthographicCamera camera;
    Box2DDebugRenderer debugRenderer;

    @Override
    public void create () {
        camera = new OrthographicCamera(1280, 720);
        camera.setToOrtho(false);
        camera.update();

        Box2D.init();
        this.debugRenderer = new Box2DDebugRenderer();
        System.out.println(camera.combined);
        System.out.println("camera.combined");

        shape = new ShapeRenderer();
        paddle = new Paddle(50, 50);

        for (int i = 0; i < 40; i++) {
            randomSize = r.nextInt(100);
            balls.add(new Ball(
                    r.nextInt(Gdx.graphics.getWidth()-randomSize*2)+randomSize,
                    r.nextInt (Gdx.graphics.getHeight()-randomSize*2)+randomSize,
                    randomSize,
                    r.nextInt(15) + 1,
                    r.nextInt(15) + 1,
                    world)
            );
        }
    }

    @Override
    public void render () {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shape.setProjectionMatrix(camera.combined);
        shape.begin(ShapeRenderer.ShapeType.Filled);
        paddle.update();
        paddle.draw(shape);
        for (Ball ball : balls) {
            ball.checkCollision(paddle);
            ball.update();
            ball.draw(shape);
        }
        shape.end();
        debugRenderer.render(world, camera.combined);
        world.step(1/60f, 6, 2);
    }
}
