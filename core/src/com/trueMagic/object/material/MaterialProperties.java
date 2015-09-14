/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trueMagic.object.material;

/**
 *
 * @author Declan Easton
 */
public class MaterialProperties {
    
    public float restitution;
    public float friction;
    public float density;
    public float electricalResistance;
    public float thermalResistance;
    public boolean collides;
    public String name;
    
    public MaterialProperties(String name, boolean collides, float restitution, float friction, float density, float electricalResistance, float thermalResistance) {
        this.name = name;
        this.restitution = restitution;
        this.density = density;
        this.electricalResistance = electricalResistance;
        this.thermalResistance = thermalResistance;
        this.friction = friction;
        this.collides = collides;
    }
    
    public static final MaterialProperties Stone = new MaterialProperties("stone", true, 0, 2, 10, 1000000, 10000);
    public static final MaterialProperties Ice = new MaterialProperties("ice", true, 0, 2, 10, 1000000, 10000);
    public static final MaterialProperties Air = new MaterialProperties("air", false, 0, 2, 10, 100, 10);
    public static final MaterialProperties AcidGas = new MaterialProperties("acid-gas", false, 0, 2, 10, 100, 10);
    
    public static MaterialProperties getMaterialProperty(String name) {
        if(name.equalsIgnoreCase("stone")) {
            return MaterialProperties.Stone;
        } else if(name.equalsIgnoreCase("ice")) {
            return MaterialProperties.Ice;
        }else if(name.equalsIgnoreCase("air")) {
            return MaterialProperties.Air;
        } else if(name.equalsIgnoreCase("acid-gas")) {
            return MaterialProperties.AcidGas;
        } else {
            return null;
        }
    }
}
