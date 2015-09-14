package com.trueMagic.manager;

import com.artemis.*;
import com.trueMagic.component.DrawShape;
import com.trueMagic.component.Material;
import com.trueMagic.component.Position;
import com.trueMagic.object.enviroment.EnviromentPoint;
import com.trueMagic.utils.collections.RegularGrid;

/**
 * Created by Declan Easton on 2015-09-07.
 */
public class EnviromentManager extends EntityManager {

    private RegularGrid<Integer> enviromentPoints;
    private Archetype enviromentPointArchetype;
    private ComponentMapper<Material> materialComponentMapper;
    private ComponentMapper<Position> positionComponentMapper;

    protected EnviromentManager(int initialContainerSize) {
        super(initialContainerSize);

        enviromentPoints = new RegularGrid<>(0.2f);
        enviromentPointArchetype = new ArchetypeBuilder()
                .add(DrawShape.class)
                .add(Position.class)
                .add(Material.class)
                .build(world);
    }

    public void setPoint(int realX, int realY, Material material) {
        if(enviromentPoints.xBound() < realX || enviromentPoints.yBound() < realY) {
            addEnviromentPoint(realX, realY, material);
        } else {
            materialComponentMapper.get(world.getEntity(enviromentPoints.get(realX, realY))).set(material);
        }
    }

    private void addEnviromentPoint(int realX, int realY, Material material) {
        Entity point = world.createEntity(enviromentPointArchetype);
        enviromentPoints.set(realX, realY, point.getId());
        materialComponentMapper.get(point).set(material);
        Position position = positionComponentMapper.get(point);
        position.x = realX;
        position.y = realY;
    }
}
