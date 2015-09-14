package com.trueMagic.entity;

import com.artemis.Entity;
import com.artemis.Manager;

import java.util.HashMap;

/**
 * Created by Declan Easton on 2015-09-08.
 */
public class Entities extends Manager {
    private HashMap<String, ProcessedArchetype> entities;

    private Entities() {}

    public void add(String name, ProcessedArchetypeBuilder builder) {
        entities.put(name, builder.build(world));
    }

    public Entity get(String name) {
        return entities.get(name).createEntity();
    }

    private static Entities instance;

    public static Entities getInstance() {
        if(instance == null) {
            instance = new Entities();
        }

        return instance;
    }
}
