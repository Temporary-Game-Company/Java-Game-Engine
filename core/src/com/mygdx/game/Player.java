package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import static com.mygdx.game.utils.Constants.PPM;

/**
 * Creates the main character, the player, Jeb.
 * Used to store player related variables and controls.
 */
public class Player {
    /*Declarations.*/
    Vector2 position;
    Rectangle body;
    private Texture playerTexture;

    /*Initializes Player Instance.*/
    public Player (Vector2 position, World world) {
        this.position = position;

        /*Initializes the player's texture and hit box.*/
        playerTexture = new Texture(this.position, "Images/Spritesheets/Test.atlas", "JebIdle001", 1f);
        body = new Rectangle(this.position, playerTexture.getSize(), 1f, false, world);
    }

    /*Updates player's position.*/
    public void update () {
        this.position = body.rectangleBody.getPosition().scl(PPM);

        /*Updates texture's position.*/
        playerTexture.update(new Vector2(this.position.x, this.position.y));
    }

    /*Draws player's texture.*/
    public void draw (SpriteBatch batch) {
        playerTexture.draw(batch);
    }

    /*Checks for input and moves player.*/
    public void move (float speed) {
        if (Gdx.input.isKeyPressed(Input.Keys.D)) { /*Right*/
            this.body.rectangleBody.applyLinearImpulse(new Vector2(speed, 0), this.position, true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) { /*Left*/
            this.body.rectangleBody.applyLinearImpulse(new Vector2(-speed, 0), this.position, true);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) { /*Jump*/
            this.body.rectangleBody.applyForceToCenter(new Vector2(0, 400), true);
        }
    }

    /*Disposes of player things*/
    public void dispose () {
        playerTexture.dispose();
    }

//    public boolean isGrounded () {
//
//    }
}
