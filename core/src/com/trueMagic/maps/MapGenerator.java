/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trueMagic.maps;

import com.artemis.World;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.trueMagic.game.PlayerProfile;
import com.trueMagic.manager.EnviromentManager;
import com.trueMagic.maps.sectionDefinition.MapSection;
import com.trueMagic.object.enviroment.EnviromentObject;
import com.trueMagic.object.enviroment.EnviromentPoint;
import com.trueMagic.object.material.Material;
import com.trueMagic.object.material.MaterialProperties;
import com.trueMagic.utils.collections.RegularGrid;
import com.trueMagic.utils.math.Point;
import com.trueMagic.utils.math.PolygonUtils;
import com.trueMagic.world.TrueMagicWorld;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Declan Easton
 */
public class MapGenerator {
    
    public static final float enviromentPointSpacing = 0.05f;
    
    public static void buildWorld(TrueMagicWorld tmWorld, MapGenerationProfile profile) {
        
    }
    
    public static void buildHome(World ceWorld, PlayerProfile profile) {
        long startTime = System.currentTimeMillis();
        MapSection[] homeSection = {SectionLoader.getHome()};
        homeSection[0].x = 0;
        homeSection[0].y = 0;
        buildEnviroment(ceWorld, homeSection);
        System.out.println("Build Time: " + (System.currentTimeMillis() - startTime));
    }
    
    private static void buildEnviroment(World ceWorld, MapSection[] sections) {
        int largestX = 0;
        int largestY = 0;
        EnviromentManager enviromentManager = ceWorld.getManager(EnviromentManager.class);
        
        for(MapSection map : sections) {
            int width = map.getWidth();
            int height = map.getHeight();
            float tileSize = map.getTileSize() / 64;
            int pointsPerSide = (int) (tileSize / enviromentPointSpacing);
            
            for(int xTile = 0; xTile <= width; xTile++) {
                for(int yTile = 0; yTile <= height; yTile++) {
                    String material = "";
                    TiledMapTile tile = map.getGroundTile(xTile, yTile);
                    if(tile != null) {
                        material = (String) tile.getProperties().get("material");
                    }
                    MaterialProperties property = null;
                    if(material != null) {
                        property = MaterialProperties.getMaterialProperty(material);
                    }
                    if(property != null) {
                        for(int xTilePos = 0; xTilePos <= pointsPerSide; xTilePos++) {
                            for(int yTilePos = 0; yTilePos <= pointsPerSide; yTilePos++) {
                                int xIndex = xTilePos + xTile * pointsPerSide;
                                int yIndex = yTilePos + yTile * pointsPerSide;
                                enviromentManager.set(xIndex * enviromentPointSpacing, yIndex * enviromentPointSpacing, ));
                                if(xIndex > largestX) {
                                    largestX = xIndex;
                                }
                                if(yIndex > largestY) {
                                    largestY = xIndex;
                                }
                            }
                        }
                    }
                }
            }
        }
        ArrayList<EnviromentObject> objects = new ArrayList<EnviromentObject>();
        int xMax = enviroment.xBound();
        int yMax = enviroment.yBound();
        for(int x = 0; x < xMax; x++) {
            for (int y = 0; y < yMax; y++) {
                EnviromentPoint point = (EnviromentPoint) enviroment.getIndex(x, y);
                if (point != null) {
                    if (validEdgePoint(enviroment, objects, x, y)) {
                        addEnviromentObject(ceWorld, objects, enviroment, x, y);
                    }
                }
            }
        }
    }
    
    private static void addEnviromentObject(World ceWorld, List<EnviromentObject> objects, RegularGrid<EnviromentPoint> points, int x, int y) {
        EnviromentPoint point = null;
        boolean continueBuilding = true;
        List<EnviromentPoint> loop = new ArrayList<EnviromentPoint>();
        loop.add(points.getIndex(x, y));
        
        while(continueBuilding) {
            if(validPointForObject(points, objects, loop, x - 1, y)) {
                point = points.getIndex(x - 1, y);
                x = x - 1;
                y = y;
            } else if(validPointForObject(points, objects, loop, x, y + 1)) {
                point = points.getIndex(x, y + 1);
                x = x;
                y = y + 1;
            } else if(validPointForObject(points, objects, loop, x + 1, y)) {
                point = points.getIndex(x + 1, y);
                x = x + 1;
                y = y;
            } else if(validPointForObject(points, objects, loop, x, y - 1)) {
                point = points.getIndex(x, y - 1);
                x = x;
                y = y - 1;
            } else if(validPointForObject(points, objects, loop, x - 1, y + 1)) {
                point = points.getIndex(x - 1, y + 1);
                x = x - 1;
                y = y + 1;
            } else if(validPointForObject(points, objects, loop, x + 1, y + 1)) {
                point = points.getIndex(x + 1, y + 1);
                x = x + 1;
                y = y + 1;
            } else if(validPointForObject(points, objects, loop, x + 1, y - 1)) {
                point = points.getIndex(x + 1, y - 1);
                x = x + 1;
                y = y - 1;
            } else if(validPointForObject(points, objects, loop, x - 1, y - 1)) {
                point = points.getIndex(x - 1, y - 1);
                x = x - 1;
                y = y - 1;
            }
            
            if(point != null) {
                loop.add(point);
            } else {
                continueBuilding = false;
            }
            point = null;
        }
        
        EnviromentPoint first = loop.get(0);
        EnviromentPoint last = loop.get(loop.size() - 1);
        float dist = (last.x - first.x) * (last.x - first.x) + (last.y - first.y) * (last.y - first.y);
        if(loop.size() > 1 && dist <= (enviromentPointSpacing * enviromentPointSpacing) + enviromentPointSpacing * 0.1) {
            loop = PolygonUtils.simplify(loop);
            objects.add(new EnviromentObject(tmWorld, loop));
        }
    }
    
    private static boolean validPointForObject(RegularGrid<EnviromentPoint> points, List<EnviromentObject> objects, List<EnviromentPoint> loop, int x, int y) {
        EnviromentPoint point = points.getIndex(x, y);
        
        if(point == null) {
            return false;
        }
        
        for(EnviromentPoint previousPoint : loop) {
            if(previousPoint == point) {
                return false;
            }
        }
        return validEdgePoint(points, objects, x, y);
    }
    
    private static boolean validEdgePoint(RegularGrid<EnviromentPoint> points, List<EnviromentObject> objects, int x, int y) {
        EnviromentPoint point = points.getIndex(x, y);
        
        if(point == null) {
            return false;
        }
        
        if(!isEdge(points, x, y)) {
            return false;
        }
        
        for(EnviromentObject object : objects) {
            if(object.onEdge(point.x, point.y)) {
                return false;
            }
        }
        
        return true;
    }
    
    private static boolean isEdge(RegularGrid<EnviromentPoint> points, int x, int y) {
        EnviromentPoint point = points.getIndex(x, y);
        if(soidGasBorder(points.getIndex(x - 1, y), point)) {
            return true;
        } else if(soidGasBorder(points.getIndex(x - 1, y + 1), point)) {
            return true;
        } else if(soidGasBorder(points.getIndex(x, y + 1), point)) {
            return true;
        } else if(soidGasBorder(points.getIndex(x + 1, y + 1), point)) {
            return true;
        } else if(soidGasBorder(points.getIndex(x + 1, y), point)) {
            return true;
        } else if(soidGasBorder(points.getIndex(x + 1, y - 1), point)) {
            return true;
        } else if(soidGasBorder(points.getIndex(x, y - 1), point)) {
            return true;
        } else if(soidGasBorder(points.getIndex(x - 1, y - 1), point)) {
            return true;
        } else {
            return false;
        }
    }
    
    private static boolean soidGasBorder(EnviromentPoint p1, EnviromentPoint p2) {
        if((p1 == null && p2 != null && p2.collides()) || (p2 == null && p1 != null && p1.collides())) {
            return true;
        } else if(p1 != null && p2 != null) {
            return (p1.collides() && !p1.collides()) || (p2.collides() && !p1.collides());
        }
        return false;
    }
}
