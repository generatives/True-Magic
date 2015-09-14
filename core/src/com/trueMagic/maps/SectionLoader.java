/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trueMagic.maps;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.maps.tiled.TideMapLoader;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.trueMagic.maps.sectionDefinition.MapSection;
import com.trueMagic.maps.sectionDefinition.MapSectionRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Declan Easton
 */
public class SectionLoader {
    
    private static HashMap<String, MapSection> maps = new HashMap<String, MapSection>();
    
    public static void load() {
        TmxMapLoader tmxMapLoader = new TmxMapLoader();
        TideMapLoader tideMapLoader = new TideMapLoader();
        FileHandle mapFolder = Gdx.files.internal("Maps");
        if(mapFolder.isDirectory()) {
            FileHandle[] mapFiles = mapFolder.list();
            for(int i = 0; i < mapFiles.length; i++) {
                FileHandle mapFile = mapFiles[i];
                if(mapFile.extension().equals("tmx")) {
                    maps.put(mapFile.nameWithoutExtension(), new MapSection(tmxMapLoader.load(mapFile.path())));
                }// else if(mapFile.extension().equals("tide")) {
                //   maps.put(mapFile.nameWithoutExtension(), new MapSection(tideMapLoader.load(mapFile.path())));
                //}
            }
        }
    }
    
    public static MapSection getHome() {
        if(maps.containsKey("Home")) {
            return maps.get("Home");
        } else {
            return null;
        }
    }
    
    public static MapSection[] requestSections(MapSectionRequest request) {
        List<MapSection> sections = new ArrayList<MapSection>();
        for(MapSection section : maps.values()) {
            if(request.isFullfilledBy(section)) {
                sections.add(section);
            }
        }
        return (MapSection[])sections.toArray();
    }
}
