package com.trueMagic.entity;

import com.artemis.Archetype;
import com.artemis.ArchetypeBuilder;
import com.artemis.Component;
import com.artemis.World;

import java.util.ArrayList;

/**
 * Created by Declan Easton on 2015-09-08.
 */
public class ProcessedArchetypeBuilder {

    private ArchetypeBuilder builder;
    private ArrayList<EntityProcessor> processors;

    public ProcessedArchetypeBuilder() {
        builder = new ArchetypeBuilder();
        processors = new ArrayList<>();
    }

    public ProcessedArchetypeBuilder add(Class<? extends Component> component) {
        builder.add(component);
        return this;
    }

    public ProcessedArchetypeBuilder remove(Class<? extends Component> component) {
        builder.remove(component);
        return this;
    }

    public ProcessedArchetypeBuilder add(EntityProcessor processor) {
        processors.add(processor);
        return this;
    }

    public ProcessedArchetypeBuilder remove(EntityProcessor processor) {
        processors.remove(processor);
        return this;
    }

    public ProcessedArchetype build(World world) {
        Archetype archetype = builder.build(world);
        return new ProcessedArchetype(archetype, (EntityProcessor[])processors.toArray(), world);
    }
}
