/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trueMagic.maps.sectionDefinition;

import com.trueMagic.utils.math.Size;
import java.util.List;

/**
 *
 * @author Declan Easton
 */
public class MapSectionRequest {
    public Size minimumSize, maximumSize;
    public List<SectionOpening> openings;
    public boolean isBoss;
    public SectionBiome biome;
    
    public boolean isFullfilledBy(MapSection section) {
        
        float sectionWidth = section.getWidth();
        float sectionHeight = section.getHeight();
        if(sectionHeight > maximumSize.height || sectionHeight < minimumSize.height
                || sectionWidth > maximumSize.width || sectionWidth < minimumSize.width) {
            return false;
        }
        
        List<SectionOpening> mapOpenings = section.getOpenings();
        for(SectionOpening opening : openings) {
            boolean openingMatched = false;
            for(SectionOpening mapOpening : mapOpenings) {
                if(SectionOpening.equals(opening, mapOpening)) {
                    openingMatched = true;
                    break;
                }
            }
            if(!openingMatched) {
                return false;
            }
        }
        
        if(biome != section.getBiome()) {
            return false;
        }
        
        if(this.isBoss != section.isBoss()) {
            return false;
        }
        
        return false;
    }
}
