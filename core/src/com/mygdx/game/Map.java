package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.utils.TiledObjectUtil;

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

    /*Returns bottom left position of the rectangle in the Spawn Location layer of the map as a vector.*/
    public Vector2 getSpawnLocation() {
        RectangleMapObject rectangleMapObject = (RectangleMapObject) map.getLayers().get("Spawn Location").getObjects().get(0);
        return new Vector2(rectangleMapObject.getRectangle().getX(), rectangleMapObject.getRectangle().getY());
    }
}
