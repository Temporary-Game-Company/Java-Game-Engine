package com.mygdx.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.utils.TiledObjectUtil;

import static com.mygdx.game.utils.Constants.PPM;

public class Map {
    TiledMap map;
    private OrthogonalTiledMapRenderer tiledMapRenderer;

    public Map (String filePath, World world) {
        this.map = new TmxMapLoader().load(filePath);
        tiledMapRenderer = new OrthogonalTiledMapRenderer(this.map);

        TiledObjectUtil.buildShapes(world, map, PPM);
    }

    public void render () {
        tiledMapRenderer.render();
    }

    public void update (OrthographicCamera camera) {
        tiledMapRenderer.setView(camera);
    }

    public void dispose () {
        this.map.dispose();
        tiledMapRenderer.dispose();
    }
}
