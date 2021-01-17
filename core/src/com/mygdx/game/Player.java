package com.mygdx.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.managers.InputManager;

import static com.mygdx.game.utils.Constants.*;

/**
 * Creates the main character, the player, Jeb.
 * Used to store player related variables and controls.
 */
public class Player {
    /*Declarations.*/
    Vector2 position;
    Rectangle hitBox;
    private Texture playerTexture;

    /*Initializes Player Instance.*/
    public Player (World world, Map map) {
        this.position = map.getSpawnLocation();

        /*Initializes the player's texture and hit box.*/
        playerTexture = new Texture(this.position, "Images/Spritesheets/Test.atlas", "JebIdle001", 1f);
        hitBox = new Rectangle(this.position, playerTexture.getSize(), 0f, 50f, false, world, BIT_PLAYER, BIT_WALL);

    }

    /*Updates player's position.*/
    public void update () {
        this.position = hitBox.body.getPosition().scl(PPM);

        /*Updates texture's position.*/
        playerTexture.update(new Vector2(this.position.x, this.position.y));
    }

    /*Draws player's texture.*/
    public void draw (SpriteBatch batch) {
        playerTexture.draw(batch);
    }

    /*Checks for input and moves player.*/
    public void move (float speed, InputManager inputManager) {
        if (inputManager.right) { /*Right*/
            this.hitBox.body.applyLinearImpulse(new Vector2(speed, 0), this.position, true);
        }
        if (inputManager.left) { /*Left*/
            this.hitBox.body.applyLinearImpulse(new Vector2(-speed, 0), this.position, true);
        }
        if (inputManager.down) { /*Right*/
            this.hitBox.body.applyLinearImpulse(new Vector2(0, -speed), this.position, true);
        }
        if (inputManager.up) { /*Left*/
            this.hitBox.body.applyLinearImpulse(new Vector2(0, speed), this.position, true);
        }

    }

    public Vector2 getPosition() {
        return this.position;
    }

    /*Disposes of player things*/
    public void dispose () {
        playerTexture.dispose();
    }

//    public boolean isGrounded () {
//
//    }
}
