package com.mygdx.game.managers;

import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.states.GameState;

public class ContactManager implements ContactListener {

    private GameState parent;

    public ContactManager (GameState parent) {
        this.parent = parent;
    }

    @Override
    public void beginContact(Contact contact) {
        Fixture a = contact.getFixtureA();
        Fixture b = contact.getFixtureB();

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
