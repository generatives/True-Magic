package com.trueMagic.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.EntitySystem;
import com.trueMagic.component.GroundCreature;
import com.trueMagic.component.LocallyControlled;
import com.trueMagic.component.Physics;
import org.jbox2d.common.Vec2;

/**
 * Created by Declan Easton on 2015-09-07.
 */
public class PlayerControlSystem extends EntitySystem {

    protected Vec2 selfPropelledForce;
    protected Vec2 smallXForce;
    protected float maximumSelfPropelledVelocity;
    protected float selfPropelledDirection;
    protected Vec2 jumpForce;

    protected int moveButtonsPressed;

    private Physics physicsComp;
    private GroundCreature groundCreatureComp;
    private ComponentMapper<Physics> physicsMap;
    private ComponentMapper<GroundCreature> groundCreatureMap;

    public PlayerControlSystem() {
        super(Aspect.all(LocallyControlled.class, Physics.class, GroundCreature.class));
    }

    @Override
    public void initialize() {
        physicsMap = ComponentMapper.getFor(Physics.class, world);
        groundCreatureMap = ComponentMapper.getFor(GroundCreature.class, world);
    }

    @Override
    protected void processSystem() {
        if(selfPropelledDirection != 0 && groundCreatureComp.groundContacts > 0) {

            float xVel = physicsComp.body.getLinearVelocity().x;
            if(xVel <= maximumSelfPropelledVelocity && xVel >= -maximumSelfPropelledVelocity) {
                physicsComp.body.applyLinearImpulse(selfPropelledForce.mul(selfPropelledDirection), physicsComp.body.getPosition(), true);
            } else {
                if(xVel < 0) {
                    physicsComp.body.applyLinearImpulse(smallXForce, physicsComp.body.getPosition(), true);
                } else {
                    physicsComp.body.applyLinearImpulse(selfPropelledForce.mul(-1), physicsComp.body.getPosition(), true);
                }
            }
        }
    }

    @Override
    public void inserted(int entityid) {
        physicsComp = physicsMap.get(world.getEntity(entityid));
        groundCreatureComp = groundCreatureMap.get(world.getEntity(entityid));
    }

    @Override
    public void removed(int entityid) {
        physicsComp = null;
    }

    public void moveLeft() {
        selfPropelledDirection = -1;
        physicsComp.body.applyLinearImpulse(selfPropelledForce.mul(selfPropelledDirection), physicsComp.body.getPosition(), true);
        moveButtonsPressed++;
    }

    public void moveRight() {
        selfPropelledDirection = 1;
        physicsComp.body.applyLinearImpulse(selfPropelledForce.mul(selfPropelledDirection), physicsComp.body.getPosition(), true);
        moveButtonsPressed++;
    }

    public void moveDown() {
        physicsComp.body.applyLinearImpulse(new Vec2(0, -selfPropelledForce.x), physicsComp.body.getPosition(), true);
    }

    public void stopMove() {
        if(moveButtonsPressed > 0) {
            moveButtonsPressed--;
            if(moveButtonsPressed <= 0) {
                selfPropelledDirection = 0;
            }
        } else {
            moveButtonsPressed = 0;
        }
    }

    public void jump() {
        if(groundCreatureComp.groundContacts > 0) {
            physicsComp.body.applyLinearImpulse(jumpForce, physicsComp.body.getPosition(), true);
        }
    }
}
