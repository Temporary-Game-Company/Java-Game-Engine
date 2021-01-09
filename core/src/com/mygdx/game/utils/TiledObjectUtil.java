package com.mygdx.game.utils;

import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapObjects;
import com.badlogic.gdx.maps.objects.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class TiledObjectUtil {
    private static float ppt = 0;

    public static void buildShapes(World world, TiledMap map, float pixels) {
        ppt = pixels;
        MapObjects objects = map.getLayers().get("Object Layer 1").getObjects();
        System.out.println("here1");
        System.out.println(objects.getCount());

        // Array<Body> bodies = new Array<Body>();
        for (MapObject object: objects) {
            System.out.println(object.getClass());
            Shape shape;
            if (object instanceof TextureMapObject) {
                continue;
            }
            if (object instanceof PolylineMapObject) {
                shape = createPolyLine((PolylineMapObject) object);
            }
            else if (object instanceof PolygonMapObject) {
                shape = createPolygon((PolygonMapObject) object);
            }
            else if (object instanceof RectangleMapObject) {
                shape = createRectangle((RectangleMapObject) object);
            }
            else if (object instanceof CircleMapObject) {
                shape = createCircle((CircleMapObject) object);
            }
            else {
                continue;
            }

            BodyDef bodyDef = new BodyDef();
            bodyDef.type = BodyType.StaticBody;
            Body body = world.createBody(bodyDef);
            body.createFixture(shape, 1f);

            // bodies.add(body);
            shape.dispose();


        }
        // return bodies;
    }

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

    private static Shape createRectangle(RectangleMapObject object) {
        Rectangle rectangle = object.getRectangle();
        PolygonShape polygonShape = new PolygonShape();
        Vector2 size = new Vector2((rectangle.x + rectangle.width*0.5f)/ppt, (rectangle.y+rectangle.height*0.5f)/ppt);
        polygonShape.setAsBox(rectangle.width*0.5f/ppt, rectangle.height*0.5f/ppt, size, 0f);
        return polygonShape;
    }

    private static Shape createCircle(CircleMapObject object) {
        Circle circle = object.getCircle();
        CircleShape circleShape = new CircleShape();
        circleShape.setRadius(circle.radius/ppt);
        circleShape.setPosition(new Vector2(circle.x/ppt, circle.y/ppt));
        return circleShape;
    }
}
