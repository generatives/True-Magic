/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trueMagic.utils.filesystem;

import com.trueMagic.game.TrueMagic;

/**
 *
 * @author Declan Easton
 */
public class FileSystemUtils {
    
    public static String getJarFolder() {
        String location = TrueMagic.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        return location.substring(0, location.lastIndexOf('/'));
    }
}
