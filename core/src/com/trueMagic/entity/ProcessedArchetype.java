package com.trueMagic.entity;

import com.artemis.Archetype;
import com.artemis.World;
import com.artemis.Entity;

/**
 * Created by Declan Easton on 2015-09-08.
 */
public class ProcessedArchetype {

    private Archetype archetype;
    private EntityProcessor[] processors;
    private World world;

    public ProcessedArchetype(Archetype archetype, EntityProcessor[] processors, World world) {
        this.processors = processors;
        this.world = world;
        this.archetype = archetype;
    }

    public Entity createEntity() {
        Entity entity =  world.createEntity(archetype);
        for (EntityProcessor processor : processors) {
            processor.Finish(entity);
        }
        return entity;
    }


}
