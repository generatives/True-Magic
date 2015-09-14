package com.trueMagic.system;

import com.artemis.Aspect;
import com.artemis.ComponentMapper;
import com.artemis.Entity;
import com.artemis.systems.EntityProcessingSystem;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.trueMagic.component.DrawShape;
import com.trueMagic.component.Physics;
import com.trueMagic.component.Position;
import com.trueMagic.utils.math.shape.Circle;
import com.trueMagic.utils.math.shape.Rectangle;
import com.trueMagic.utils.math.shape.Shape;

/**
 * Created by Declan Easton on 2015-09-07.
 */
public class ShapeDrawingSystem extends EntityProcessingSystem {

    private ComponentMapper<DrawShape> shapeMap;
    private ComponentMapper<Physics> physicsMap;
    private ComponentMapper<Position> positionMap;
    private CameraFollowingSystem cameraSystem;

    public ShapeRenderer renderer;

    public ShapeDrawingSystem(CameraFollowingSystem cameraSystem) {
        super(Aspect.all(DrawShape.class).one(Physics.class, Position.class));

        this.cameraSystem = cameraSystem;
        this.renderer = new ShapeRenderer();
    }

    @Override
    public void initialize() {
        shapeMap = ComponentMapper.getFor(DrawShape.class, world);
        physicsMap = ComponentMapper.getFor(Physics.class, world);
        positionMap = ComponentMapper.getFor(Position.class, world);
    }

    @Override
    public void begin() {
        renderer.begin();
    }

    @Override
    protected void process(Entity entity) {
        renderer.setProjectionMatrix(cameraSystem.getCamera().combined);

        DrawShape shapeComp = shapeMap.get(entity);
        Physics physicsComp = physicsMap.get(entity);
        Position positionComp = new Position();
        if(physicsComp != null) {
            positionComp.x = physicsComp.body.getPosition().x;
            positionComp.y = physicsComp.body.getPosition().y;
        } else {
            positionComp = positionMap.get(entity);
        }
        renderer.set(shapeComp.shapeType);
        renderer.setColor(shapeComp.color);
        Shape shape = shapeComp.shape;
        if(shape.getType() == Shape.CIRCLE) {
            Circle circle = (Circle)shape;
            renderer.circle((positionComp.x + circle.x) * 64, (positionComp.y + circle.y) * 64, circle.radius * 64);
        } else if(shape.getType() == Shape.RECTANGLE) {
            Rectangle rectangle = (Rectangle)shape;
            renderer.rect((positionComp.x + rectangle.x) * 64, (positionComp.y + rectangle.y) * 64, rectangle.width * 64, rectangle.height * 64);
        }
    }

    @Override
    public void end() {
        renderer.end();
    }
}
