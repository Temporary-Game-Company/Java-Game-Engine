package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.game.Application;
import com.mygdx.game.Camera;
import com.mygdx.game.managers.GameStateManager;
import com.mygdx.game.managers.InputManager;

public abstract class GameState extends Stage {

    /*References*/
    protected GameStateManager gameStateManager;
    protected Application app;
    protected SpriteBatch batch;
    protected Camera camera;

    InputManager inputManager = new InputManager();

    protected GameState(GameStateManager gameStateManager) {
        this.gameStateManager = gameStateManager;
        this.app = gameStateManager.application();
        batch = app.getBatch();
        camera = app.getCamera();

        Gdx.input.setInputProcessor(inputManager);
    }


    public void resize(int w, int h) {
        camera.setToOrtho(false, w, h);
    }

    public abstract void update(float delta);
    public abstract void render();
    public abstract void dispose();
}
