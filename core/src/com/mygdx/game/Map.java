package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.utils.TiledObjectUtil;

import static com.mygdx.game.utils.Constants.PPM;

public class Map {
    /* Declarations.*/
    TiledMap map;
    private OrthogonalTiledMapRenderer tiledMapRenderer;

    /*Initializes Map*/
    public Map (String filePath, World world) {
        this.map = new TmxMapLoader().load(filePath);
        tiledMapRenderer = new OrthogonalTiledMapRenderer(this.map);

        /*Builds the shapes in the map's collision layer*/
        TiledObjectUtil.buildShapes(world, map);
    }

    /*Renders the map.*/
    public void render () {
        tiledMapRenderer.render();
    }

    /*Updates the part of the map you can see and it's position.*/
    public void update (OrthographicCamera camera) {
        tiledMapRenderer.setView(camera);
    }

    /*Disposes of map things.*/
    public void dispose () {
        this.map.dispose();
        tiledMapRenderer.dispose();
    }
}
