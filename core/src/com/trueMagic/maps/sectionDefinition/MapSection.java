/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trueMagic.maps.sectionDefinition;

import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.trueMagic.utils.math.Side;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Declan Easton
 */
public class MapSection {
    
    private final TiledMap map;
    private final TiledMapTileLayer ground;
    private final List<SectionOpening> openings;
    private final boolean isBoss;
    private final SectionBiome biome;
    public int x, y;
    
    public MapSection(TiledMap map) {
        this.map = map;
        this.ground = (TiledMapTileLayer)map.getLayers().get("Ground");
        
        openings = new ArrayList<SectionOpening>();
        
        MapProperties properties = map.getProperties();
        
        String isBossString = properties.get("isBoss", String.class);
        isBoss = isBossString.equalsIgnoreCase("true");
        
        String biomeString = properties.get("biome", String.class);
        biome = SectionBiome.valueOf(biomeString);
        
        parseOpenings(properties.get("openings", String.class), openings);
        
    }
    
    private void parseOpenings(String openings, List<SectionOpening> list) {
        String[] openingArray = openings.split(";");
        for(String openingString : openingArray) {
            if(!openingString.equals("")) {
                SectionOpening opening = new SectionOpening();
                String[] infoArray = openingString.split(",");
                opening.side = Side.valueOf(infoArray[0].trim());
                opening.position = Integer.parseInt(infoArray[1].trim());
                opening.length = Integer.parseInt(infoArray[2].trim());
                list.add(opening);
            }
        }
    }
    
    public TiledMapTile getGroundTile(int x, int y) {
        Cell cell = ground.getCell(x, y);
        if(cell != null) {
            return cell.getTile();
        }
        return null;
    }
    
    public int getWidth() {
        return ground.getWidth();
    }
    
    public int getHeight() {
        return ground.getHeight();
    }
    
    public float getTileSize() {
        return ground.getTileHeight();
    }
    
    public List<SectionOpening> getOpenings() {
        return openings;
    }
    
    public SectionBiome getBiome() {
        return biome;
    }
    
    public boolean isBoss() {
        return isBoss;
    }
}
