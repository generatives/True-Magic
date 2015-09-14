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
public class Material {
    
    public final MaterialProperties properties;
    private float heat;
    private float charge;
    
    public Material(MaterialProperties properties, float initialHeat, float initialCharge) {
        this.properties = properties;
    }
    
    public static void transmit(Material one, Material two, float deltaTime) {
        float heatChange = deltaTime * (one.heat - two.heat) / (one.properties.thermalResistance + two.properties.thermalResistance);
        one.heat -= heatChange;
        two.heat += heatChange;
        
        float chargeChange = deltaTime * (one.charge - two.charge) / (one.properties.electricalResistance + two.properties.electricalResistance);
        one.charge -= chargeChange;
        two.charge += chargeChange;
    }
}
