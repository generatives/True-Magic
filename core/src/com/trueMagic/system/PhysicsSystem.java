package com.trueMagic.system;

import com.artemis.Aspect;
import com.artemis.EntitySystem;
import com.trueMagic.component.Physics;
import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.contacts.Contact;

/**
 * Created by Declan Easton on 2015-09-07.
 */
public class PhysicsSystem extends EntitySystem implements ContactListener {

    public World physicalWorld;

    public PhysicsSystem() {
        super(Aspect.all(Physics.class));

        physicalWorld = new World(new Vec2(0, -10));
        physicalWorld.setContactListener(this);
    }

    @Override
    protected void processSystem() {
        physicalWorld.step(world.delta, 6, 12);
    }

    @Override
    public void beginContact(Contact contact) {

    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {

    }
}
