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
    Texture playerTexture;

    public Player (Vector2 position, World world) {
        this.position = position;

        playerTexture = new Texture(this.position, "Spritesheets/Player.atlas", "001", 0.30f);
        body = new Rectangle(this.position, playerTexture.sprite.getWidth(), playerTexture.sprite.getHeight(), 1f, false, world);
    }

    public void update () {
        this.position = body.rectangleBody.getPosition().scl(PPM);

        playerTexture.update(new Vector2(this.position.x-playerTexture.sprite.getWidth()/2, this.position.y-playerTexture.sprite.getHeight()/2));
        System.out.println(position);
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

//    public boolean isGrounded () {
//
//    }
}
