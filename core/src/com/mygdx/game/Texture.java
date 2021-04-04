package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * Used to place a texture.
 */
public class Texture {
    /*Declarations*/
    Vector2 position;
    float scale;

    private TextureAtlas textureAtlas;
    private Sprite sprite;
    private TextureRegion textureRegion;

    /*Initializes a texture.*/
    public Texture(Vector2 position, String filePath, String name, float scale) {
        this.position = position;
        this.scale = scale;

        /*Sets the sprite to a region of the texture atlas.*/
        this.textureAtlas = new TextureAtlas(Gdx.files.internal(filePath));
        this.textureRegion = textureAtlas.findRegion(name);
        this.sprite = new Sprite(textureRegion);

        /*Sets the position and size of the sprite.*/
        this.sprite.setPosition(this.position.x, this.position.y);
        this.sprite.setSize(sprite.getWidth()*scale, sprite.getHeight()*scale);
    }

    /*Updates the texture's location.*/
    public void update(Vector2 position) {
        this.position = position;
        this.sprite.setPosition(this.position.x-this.sprite.getWidth()/2, this.position.y-this.sprite.getHeight()/2);

    }

    /*Draws the texture.*/
    public void draw(SpriteBatch batch) {
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

    /*Returns the texture's size as a size vector.*/
    public Vector2 getSize () {
        return new Vector2(this.sprite.getWidth(), this.sprite.getHeight());
    }

    public void setSize (Vector2 size) {
        this.sprite.setSize(size.x, size.y);
    }

    /*Disposes of texture things.*/
    public void dispose () {
        this.textureAtlas.dispose();
    }

}
