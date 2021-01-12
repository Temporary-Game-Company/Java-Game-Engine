package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.Map;
import com.mygdx.game.Player;
import com.mygdx.game.Rectangle;
import com.mygdx.game.managers.GameStateManager;

import static com.mygdx.game.utils.Constants.PPM;

public class PlayState extends GameState {

    /*Declarations*/
    Map map;
    Player player;

    private World world;
    Box2DDebugRenderer debugRenderer;

    private float acc, timeStep;

    public PlayState(GameStateManager gameStateManager) {
        super(gameStateManager);

        this.acc = 0f;
        this.timeStep = 1/400f;

        Box2D.init();  /* Initializes Box2D (physics engine).*/

        /*Creates a world with -9.8 gravity.*/
        this.world = new World(new Vector2(0f,-9.8f), true);
        /* Creates a debug renderer so that we can see the Box2D bodies for now*/
        this.debugRenderer = new Box2DDebugRenderer();

        /* Initializes Map and the Player.*/
        map = new Map("Maps/Test2.tmx", world);
        player = new Player(new Vector2(100, 100), world);


        /* Instantiates lines from origin for 5000 pixels in direction of positive x and y axis.*/
        new Rectangle(new Vector2(0, 0), new Vector2(5000,0), 3f, true, world);
        new Rectangle(new Vector2(0, 0), new Vector2(0,5000), 3f, true, world);
    }

    @Override
    public void update(float delta) {
        step(delta);

        /*Updates position of camera, map, and batch.*/
        camera.update(player.getPosition());
        map.update(camera.getCamera());
        batch.setProjectionMatrix(camera.getCamera().combined);
    }

    @Override
    public void render() {/* Clear*/
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        /* Update*/
        update(Gdx.graphics.getDeltaTime());
        player.update();

        /* Draw*/
        player.move(0.35f);
        player.update();
        player.draw(batch);

        map.render();

        /* Debug Renderer*/
        debugRenderer.render(world, camera.getCamera().combined.scl(PPM));

    }

    private void step(float delta) {
        if (delta > 0.25) {
            delta = 0.25f;
        }
        System.out.println(delta);
        acc += delta;
        while (acc >= timeStep) {
            world.step(timeStep, 6, 2);
            acc -= timeStep;
        }
    }

    @Override
    public void dispose() {
        world.dispose();
        debugRenderer.dispose();
        map.dispose();
        player.dispose();
    }
}
