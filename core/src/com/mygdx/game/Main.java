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
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.ArrayList;
import java.util.Random;
import static com.mygdx.game.utils.Constants.PPM;

public class Main extends ApplicationAdapter {
    ShapeRenderer shape;  // Used to render white shapes.
    ArrayList<Ball> balls = new ArrayList<>();
    Ball ball;
    Paddle paddle;
    Random r = new Random();
    int randomSize;

    private World world;

    private OrthographicCamera camera;
    private Viewport viewport;
    private SpriteBatch batch;

    Box2DDebugRenderer debugRenderer;

    @Override
    public void create () {
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);
        camera.update();

        this.world = new World(new Vector2(0,0), true);

        this.debugRenderer = new Box2DDebugRenderer();

        paddle = new Paddle(50, 50, 50f, 50f, true, world);
        ball = new Ball(50, 50, 100, false, world);
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
    }

    @Override
    public void render () {
        update(Gdx.graphics.getDeltaTime());
        Box2D.init();

        //Render
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        for (Ball ball : balls) {
//            ball.checkCollision(paddle);
//            ball.move();
//            ball.draw(shape);
//        }
        ball.move();
        debugRenderer.render(world, camera.combined.scl(PPM));
    }

    @Override
    public void dispose() {
        world.dispose();
        debugRenderer.dispose();
    }

    public void update (float delta) {
        world.step(1/60f, 6, 2);

        cameraUpdate(delta);
    }

    public void cameraUpdate (float delta) {
        Vector3 position = camera.position;
        position.x = ball.ballBody.getPosition().x * PPM;
        position.y = ball.ballBody.getPosition().y * PPM;
        camera.position.set(position);

        camera.update();
    }


}
