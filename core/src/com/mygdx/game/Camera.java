package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

import static com.mygdx.game.utils.Constants.PPM;

/**
 * Creates a camera the size of the window.
 */
public class Camera {
    /* Declarations.*/
    public OrthographicCamera camera;
    float width;
    float height;
    float scale;

    public Camera (float scale) {
        /*Initializations*/
        this.width = Gdx.graphics.getWidth()*scale;
        this.height = Gdx.graphics.getHeight()*scale;
        this.scale = scale;

        this.camera = new OrthographicCamera();
        this.camera.setToOrtho(false, this.width, this.height); /*Sets size of camera display*/
        this.camera.update(); /*Updates camera*/


    }

    /**
     * Updates position of camera to follow a central position.
     */
    public void update (Vector2 centerPosition) {
        Vector3 position = camera.position;  /*Gets the camera position.*/

        /*Sets position but moves slower. (Interpolation)*/
        position.x = position.x + (centerPosition.x-position.x) * 0.2f;
        position.y = position.y + (centerPosition.y-position.y) * 0.2f;

        /*Updates camera with position.*/
        camera.update();
    }

    public void setToOrtho(boolean b, int w, int h) {
        camera.setToOrtho(false, w, h);
    }

    public OrthographicCamera getCamera() {
        return camera;
    }
}
