package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import static com.mygdx.game.utils.Constants.PPM;

public class Texture {
    Vector2 position;
    float scale;

    TextureAtlas textureAtlas;
    Sprite sprite;
    TextureRegion textureRegion;

    public Texture(Vector2 position, String filePath, String name, float scale) {
        this.position = position;
        this.scale = scale;

        this.textureAtlas = new TextureAtlas(Gdx.files.internal(filePath));
        this.textureRegion = textureAtlas.findRegion(name);
        this.sprite = new Sprite(textureRegion);

        this.sprite.setPosition(this.position.x, this.position.y);
        this.sprite.setSize(sprite.getWidth()*scale, sprite.getHeight()*scale);
    }

    public void update(Vector2 position) {
        this.position = position;
        this.sprite.setPosition(this.position.x-this.sprite.getWidth()/2, this.position.y-this.sprite.getHeight()/2);

    }

    public void draw(SpriteBatch batch) {
        batch.begin();
        sprite.draw(batch);
        batch.end();
    }

}
