/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trueMagic.textureGeneration;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;

/**
 *
 * @author Declan Easton
 */
public class TextureGenerator {
    
    public static Texture platform(int x, int y) {
        Pixmap pixmap = new Pixmap(x, y, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLACK);
        pixmap.fill();
        return new Texture(pixmap);
    }
    
    public static Texture player(int x, int y) {
        Pixmap pixmap = new Pixmap(x, y, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLUE);
        pixmap.fill();
        return new Texture(pixmap);
    }
    
    public static Texture projectile(int x, int y) {
        Pixmap pixmap = new Pixmap(x, y, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.fill();
        return new Texture(pixmap);
    }
}
