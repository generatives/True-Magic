package com.trueMagic.component;

import com.artemis.Component;

/**
 * Created by Declan Easton on 2015-09-07.
 */
public class Material extends Component {
    private float heat;
    private float charge;
    public float restitution;
    public float friction;
    public float density;
    public float electricalResistance;
    public float thermalResistance;
    public boolean collides;
    public String name;

    public void set(Material material) {
        this.heat = material.heat;
        this.charge = material.charge;
        this.restitution = material.restitution;
        this.friction = material.friction;
        this.density = material.density;
        this.electricalResistance = material.electricalResistance;
        this.thermalResistance = material.thermalResistance;
        this.collides = material.collides;
        this.name = material.name;
    }
}
