package com.mygdx.game.managers;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.Application;
import com.mygdx.game.states.GameState;
import com.mygdx.game.states.PlayState;
import com.mygdx.game.states.SplashState;

import java.util.Stack;

public class GameStateManager {

    // Application Reference
    private final Application app;

    private Stack<GameState> states;

    /*Enumerates States*/
    public enum State {
        SPLASH,
        MAINMENU,
        PLAY
    }

    /*Initializes Game State Manager*/
    public GameStateManager(final Application app) {
        this.app = app;
        this.states = new Stack<>();
        this.setState(State.SPLASH);  /*Initial State*/
    }

    public Application application() {
        return app;
    }

    public void update(float delta) {
        states.peek().update(delta);
    }

    public void render() {
        states.peek().render();
    }

    public void dispose() {
        for(GameState gameState : states) {
            gameState.dispose();
        }
        states.clear();
    }

    public void resize(int w, int h) {
        states.peek().resize(w, h);
    }

    public void setState(State state) {
        if (states.size() >= 1) {
            states.pop().dispose();
        }
        states.push(getState(state));
    }

    public GameState getState(State state) {
        switch(state) {
            case SPLASH: return new SplashState(this);
            case PLAY: return new PlayState(this);
            case MAINMENU: return null;
        }
        return null;
    }
}
