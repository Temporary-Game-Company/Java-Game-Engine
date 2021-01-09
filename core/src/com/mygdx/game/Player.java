package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import static com.mygdx.game.utils.Constants.PPM;

public class Player {
    Vector2 position;
    Rectangle body;
    private Texture playerTexture;

    public Player (Vector2 position, World world) {
        this.position = position;

        playerTexture = new Texture(this.position, "Images/Spritesheets/Test.atlas", "JebIdle001", 1f);
        body = new Rectangle(this.position, playerTexture.getSize(), 1f, false, world);
    }

    public void update () {
        this.position = body.rectangleBody.getPosition().scl(PPM);

        playerTexture.update(new Vector2(this.position.x, this.position.y));
    }

    public void draw (SpriteBatch batch) {
        playerTexture.draw(batch);
    }

    public void move (float speed) {
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            this.body.rectangleBody.applyLinearImpulse(new Vector2(speed, 0), this.position, true);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            this.body.rectangleBody.applyLinearImpulse(new Vector2(-speed, 0), this.position, true);
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            this.body.rectangleBody.applyForceToCenter(new Vector2(0, 400), true);
        }
    }

    public void dispose () {
        playerTexture.dispose();
    }

//    public boolean isGrounded () {
//
//    }
}
