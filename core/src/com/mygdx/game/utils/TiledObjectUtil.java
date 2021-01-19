package com.mygdx.game.utils;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

import static com.mygdx.game.utils.Constants.*;

public class TiledObjectUtil {
    private static float ppt = 0;

    public static void buildShapes(World world, TiledMap map) {
        ppt = PPM;
        MapLayers layers = map.getLayers();

        for (MapLayer layer: layers) {

            if (layer.getName().equals("Spawn Location")) {
                continue;
            }

            MapObjects objects = layer.getObjects();

            // Array<Body> bodies = new Array<Body>();
            for (MapObject object: objects) { /*Iterates through objects in collision layer of tiled map.*/
                Shape shape;

                /*Checks for object type and creates a shape for it.*/
                if (object instanceof TextureMapObject) {
                    continue;
                }
                if (object instanceof PolylineMapObject) {
                    shape = createPolyLine((PolylineMapObject) object);
                } else if (object instanceof PolygonMapObject) {
                    shape = createPolygon((PolygonMapObject) object);
                } else if (object instanceof RectangleMapObject) {
                    shape = createRectangle((RectangleMapObject) object);
                } else if (object instanceof CircleMapObject) {
                    shape = createCircle((CircleMapObject) object);
                } else {
                    continue;
                }

                /*Attaches a body to the shape and creates a fixture.*/
                BodyDef bodyDef = new BodyDef();
                bodyDef.type = BodyType.StaticBody;
                Body body = world.createBody(bodyDef);
                body.setUserData(layer.getName());
                FixtureDef fixtureDef = new FixtureDef();
                fixtureDef.friction = 1f;
                fixtureDef.filter.categoryBits = BIT_WALL;
                fixtureDef.filter.maskBits = -1;
                fixtureDef.shape = shape;
                body.createFixture(fixtureDef);  /*For collision.*/

                // bodies.add(body);
                shape.dispose();

            }
        }
        // return bodies;
    }

    /*Returns a chain shape of the tiled map polyline.*/
    private static Shape createPolyLine(PolylineMapObject object) {
        float[] vertices = object.getPolyline().getTransformedVertices();
        Vector2[] worldVertices = new Vector2[vertices.length/2];

        for (int i = 0; i<vertices.length/2; ++i) {
            worldVertices[i] = new Vector2();
            worldVertices[i].x = vertices[i*2]/ppt;
            worldVertices[i].y = vertices[i*2 + 1]/ppt;
        }

        ChainShape chain = new ChainShape();
        chain.createChain(worldVertices);
        return chain;
    }

    /*Returns a polygon shape of the tiled map polygon.*/
    private static Shape createPolygon(PolygonMapObject object) {
        PolygonShape polygonShape = new PolygonShape();
        float[] vertices = object.getPolygon().getTransformedVertices();

        float[] worldVertices = new float[vertices.length];

        for (int i = 0; i < vertices.length; ++i) {
            worldVertices[i] = vertices[i]/ppt;
        }

        polygonShape.set(worldVertices);
        return polygonShape;
    }

    /*Returns a rectangle shape of the tiled map rectangle.*/
    private static Shape createRectangle(RectangleMapObject object) {
        Rectangle rectangle = object.getRectangle();
        PolygonShape polygonShape = new PolygonShape();
        Vector2 size = new Vector2((rectangle.x + rectangle.width*0.5f)/ppt, (rectangle.y+rectangle.height*0.5f)/ppt);
        polygonShape.setAsBox(rectangle.width*0.5f/ppt, rectangle.height*0.5f/ppt, size, 0f);
        return polygonShape;
    }

    /*Returns a circle shape of the tiled map circle.*/
    private static Shape createCircle(CircleMapObject object) {
        Circle circle = object.getCircle();
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(circle.radius/ppt);
        circleShape.setPosition(new Vector2(circle.x/ppt, circle.y/ppt));
        return circleShape;
    }
}
