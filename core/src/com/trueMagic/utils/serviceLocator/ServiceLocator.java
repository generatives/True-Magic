/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trueMagic.utils.serviceLocator;

import com.trueMagic.game.TrueMagic;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.scannotation.AnnotationDB;
import org.scannotation.ClasspathUrlFinder;

/**
 *
 * @author Declan Easton
 */
public class ServiceLocator {
    
    private ServiceLocator() {
//        final MethodReporter reporter = new MethodReporter() {
//
//            @SuppressWarnings("unchecked")
//            @Override
//            public Class<? extends Annotation>[] annotations() {
//                return new Class[]{ServiceFlag.class};
//            }
//
//            @Override
//            public void reportMethodAnnotation(Class<? extends Annotation> annotation,
//                String className, String methodName) {
//                Class clazz = null;
//                try {
//                    clazz = Class.forName(className);
//                } catch (ClassNotFoundException ex) {
//                    Logger.getLogger(ServiceLocator.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                ServiceFlag flag = (ServiceFlag) clazz.getAnnotation(ServiceFlag.class);
//                Class serviceType = flag.service();
//                
//                Constructor serviceConstructor = null;
//                try {
//                    serviceConstructor = clazz.getConstructor();
//                } catch (NoSuchMethodException ex) {
//                    Logger.getLogger(ServiceLocator.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (SecurityException ex) {
//                    Logger.getLogger(ServiceLocator.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                
//                Object object = null;
//                try {
//                    object = serviceConstructor.newInstance();
//                } catch (InstantiationException ex) {
//                    Logger.getLogger(ServiceLocator.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (IllegalAccessException ex) {
//                    Logger.getLogger(ServiceLocator.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (IllegalArgumentException ex) {
//                    Logger.getLogger(ServiceLocator.class.getName()).log(Level.SEVERE, null, ex);
//                } catch (InvocationTargetException ex) {
//                    Logger.getLogger(ServiceLocator.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                
//                if(serviceLists.containsKey(serviceType)) {
//                    serviceLists.get(serviceType).add(object);
//                } else {
//                    serviceLists.put(serviceType, new ArrayList<Object>());
//                    serviceLists.get(serviceType).add(object);
//                }
//            }
//
//        };
//        final AnnotationDetector cf = new AnnotationDetector(reporter);
//        try {
//            File f = new File(ServiceLocator.class.getProtectionDomain().getCodeSource().getLocation().getPath());
//            cf.detect("com.trueMagic");
//        } catch (IOException ex) {
//            Logger.getLogger(ServiceLocator.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
//        String[] paths = System.getProperty("java.class.path").split(";");
//        ArrayList<URL> urls = new ArrayList<URL>();
//        URL url = null;
//        for(String testPath : paths) {
//            if(new File(testPath).exists() && testPath.contains("com.trueMagic.TrueMagic")) {
//                URL string = ClasspathUrlFinder.findResourceBase(testPath);
//                urls.add(url);
//            }
//        }
        URL string = ClasspathUrlFinder.findResourceBase("com.trueMagic.TrueMagic");
        AnnotationDB db = new AnnotationDB();
        try {
            db.scanArchives(string);
        } catch (IOException ex) {
            Logger.getLogger(ServiceLocator.class.getName()).log(Level.SEVERE, null, ex);
        }
        Map<String,Set<String>> map = db.getAnnotationIndex();
        int a = 1;
    }
    
    public static ServiceLocator getInstance() {
        return ServiceLocatorHolder.INSTANCE;
    }
    
    private static class ServiceLocatorHolder {
        private static final ServiceLocator INSTANCE = new ServiceLocator();
    }
    
    public final HashMap<Class, ArrayList<Object>> serviceLists = new HashMap<Class, ArrayList<Object>>();
    
    public Object[] getServices(Class objectClass) {
        return serviceLists.get(objectClass).toArray();
    }
    
    public Object getService(Class objectClass) {
        return serviceLists.get(objectClass).get(0);
    }
}
