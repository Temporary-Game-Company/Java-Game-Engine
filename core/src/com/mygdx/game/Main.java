package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import static com.mygdx.game.utils.Constants.PPM;

public class Main extends ApplicationAdapter {
    Player player;
    Rectangle floor;

    private World world;

    private OrthographicCamera camera;
    public SpriteBatch batch;

    Box2DDebugRenderer debugRenderer;

    @Override
    public void create () {
        // Initializes variables for width/height.
        float width = Gdx.graphics.getWidth();
        float height = Gdx.graphics.getHeight();

        // Creates camera, sets properties, updates it.
        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);
        camera.update();

        Box2D.init();  // Initializes Box2D.

        this.world = new World(new Vector2(0f,-9.8f), true);
        this.debugRenderer = new Box2DDebugRenderer();


        player = new Player(new Vector2(50, 100), world);
        floor = new Rectangle(new Vector2(-100, 0), 500, 1, 3f, true, world);

        batch = new SpriteBatch();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
    }

    @Override
    public void render () {
        // Clear
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Update
        update(Gdx.graphics.getDeltaTime());
        player.update();

        // Draw
        player.move(0.5f);
        player.update();
        player.draw(batch);

        //Debug Renderer
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
        batch.setProjectionMatrix(camera.combined);
    }

    public void cameraUpdate (float delta) {
        Vector3 position = camera.position;
        position.x = player.body.rectangleBody.getPosition().x * PPM;
        position.y = player.body.rectangleBody.getPosition().y * PPM;
        camera.position.set(position);

        camera.update();
    }


}
