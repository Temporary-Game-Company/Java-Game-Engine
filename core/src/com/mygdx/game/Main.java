package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2D;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import static com.mygdx.game.utils.Constants.PPM;

public class Main extends ApplicationAdapter {

    // Declarations.
    private World world;

    private Camera mainCamera;

    public SpriteBatch batch;

    Map map;
    Player player;

    Box2DDebugRenderer debugRenderer;

    @Override
    public void create () {  // Runs once when game starts.
        // Sets color of the function which clears the last frame. (Currently purple for contrast)
        Gdx.gl.glClearColor(0.188f, 0.098f, 0.2039f, 1f);

        // Creates the camera (the camera shows the things that are rendered).
        mainCamera = new Camera();

        Box2D.init();  // Initializes Box2D (physics engine).

        // Creates a world with -9.8 gravity.
        this.world = new World(new Vector2(0f,-9.8f), true);
        // Creates a debug renderer so that we can see the Box2D bodies for now
        this.debugRenderer = new Box2DDebugRenderer();

        // Initializes Map and the Player.
        map = new Map("Maps/Test2.tmx", world);
        player = new Player(new Vector2(100, 100), world);


        // Instantiates lines from origin for 5000 pixels in direction of positive x and y axis.
        new Rectangle(new Vector2(0, 0), new Vector2(5000,0), 3f, true, world);
        new Rectangle(new Vector2(0, 0), new Vector2(0,5000), 3f, true, world);

        // Initializes Batch.
        batch = new SpriteBatch();
    }

    @Override  // What happens when window size changes.
    public void resize(int width, int height) {
        mainCamera.camera.setToOrtho(false, width, height);
    }

    @Override  // Runs every frame.
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

        map.render();

        //Debug Renderer
        debugRenderer.render(world, mainCamera.camera.combined.scl(PPM));
    }

    @Override  // Runs when program closes. Used to dispose of things for efficiency.
    public void dispose() {
        world.dispose();
        debugRenderer.dispose();
        batch.dispose();
        map.dispose();
        player.dispose();
    }

    public void update (float delta) {  // Updates some things.
        world.step(1/60f, 6, 2);  // Moves time forward by a sixty-th.

        //Updates position of camera, map, and batch.
        mainCamera.update(player.body.rectangleBody.getPosition().x * PPM, player.body.rectangleBody.getPosition().y * PPM, delta);
        map.update(mainCamera.camera);
        batch.setProjectionMatrix(mainCamera.camera.combined);
    }

}
