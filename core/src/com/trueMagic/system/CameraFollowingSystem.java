package com.trueMagic.system;

import com.artemis.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.trueMagic.component.CameraTarget;
import com.trueMagic.component.Physics;

/**
 * Created by Declan Easton on 2015-09-07.
 */
public class CameraFollowingSystem extends EntitySystem {
    private int followingId;
    private Physics physicsComp;
    private ComponentMapper<Physics> physicsMapper;
    private OrthographicCamera camera;

    public CameraFollowingSystem() {
        super(Aspect.all(CameraTarget.class, Physics.class));

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(1500, 1500 * (h / w));
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();
    }

    @Override
    public void initialize() {
        physicsMapper = ComponentMapper.getFor(Physics.class, world);
    }

    @Override
    protected void processSystem() {
        camera.position.set(physicsComp.body.getPosition().x * 64,
                physicsComp.body.getPosition().y * 64, 0);
        camera.update();
    }

    @Override
    protected void inserted(int entityid) {
        if(entityid != followingId) {
            physicsComp = physicsMapper.get(world.getEntity(entityid));
            followingId = entityid;
        }
    }

    @Override
    protected void removed(int entityid) {
        if(entityid == followingId) {
            physicsComp = null;
            followingId = -1;
        }
    }

    public Vector2 unprojectVector(Vector2 vector) {
        Vector3 tmp = new Vector3(vector.x, vector.y, 0);
        Vector3 result3 = camera.unproject(tmp);
        return new Vector2(result3.x, result3.y);
    }

    public Camera getCamera() {
        return camera;
    }
}
