package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;

import static com.mygdx.game.utils.Constants.PPM;

/**
 * Creates a camera the size of the window.
 */
public class Camera {
    /* Declarations.*/
    OrthographicCamera camera;
    float width;
    float height;

    public Camera () {
        /*Initializations*/
        this.width = Gdx.graphics.getWidth();
        this.height = Gdx.graphics.getHeight();

        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, this.width, this.height); /*Sets size of camera display*/
        this.camera.update(); /*Updates camera*/


    }

    /**
     * Updates position of camera.
     */
    public void update (float x, float y, float delta) {
        Vector3 position = camera.position;
        position.x = x;
        position.y = y;
        camera.position.set(position);
        camera.update();
    }
}
