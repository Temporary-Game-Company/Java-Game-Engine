package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.managers.GameStateManager;

public class Application extends ApplicationAdapter {

    /* Settings.*/
    public static final String title = "Jebja";
    public static final int width = 1920;
    public static final int height = 1080;
    public static final boolean fullScreen = true;
    public static final float scale = 1.0f;

    /* Declarations.*/

    private Camera mainCamera;

    public SpriteBatch batch;

    private GameStateManager gameStateManager;


    @Override
    public void create () {  /* Runs once when game starts.*/

        /* Sets color of the function which clears the last frame. (Currently purple for contrast)*/
        Gdx.gl.glClearColor(0.188f, 0.098f, 0.2039f, 1f);

         /*Initializes Batch.*/
        batch = new SpriteBatch();

        /* Creates the camera (the camera shows the things that are rendered).*/
        mainCamera = new Camera(scale);

        gameStateManager = new GameStateManager(this);

    }

    @Override  /* What happens when window size changes.*/
    public void resize(int width, int height) {
        gameStateManager.resize((int) (width/scale), (int) (height/scale));
    }

    @Override  /* Runs every frame.*/
    public void render () {
        gameStateManager.update(Gdx.graphics.getDeltaTime());
        gameStateManager.render();
    }

    @Override  /* Runs when program closes. Used to dispose of things for efficiency.*/
    public void dispose() {
        gameStateManager.dispose();
        batch.dispose();

    }

    public SpriteBatch getBatch() {
        return batch;
    }

    public Camera getCamera() {
        return mainCamera;
    }
}
