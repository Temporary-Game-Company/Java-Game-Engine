package com.mygdx.game.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Texture;
import com.mygdx.game.managers.GameStateManager;

public class SplashState extends GameState {

    float acc = 0f;
    Texture texture;


    public SplashState(GameStateManager gameStateManager) {
        super(gameStateManager);
        texture = new Texture(new Vector2(0,0),"Images/Spritesheets/test.atlas", "Press Enter!", 6.7f);

        texture.setSize(new Vector2(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
    }

    @Override
    public void update(float delta) {

        /*Switches to Play after 3 Delta.*/
        acc += delta;
        if (Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            gameStateManager.setState(GameStateManager.State.PLAY);

        }
    }

    @Override
    public void render() {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);  /*Clears Background*/

        batch.setProjectionMatrix(camera.getCamera().combined);
        texture.draw(batch);

    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
