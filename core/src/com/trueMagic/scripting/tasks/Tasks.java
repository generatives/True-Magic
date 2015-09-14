/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trueMagic.scripting.tasks;

/**
 *
 * @author Declan Easton
 */
public class Tasks {
    public static Task tasks;
    
    public static void addTask(Task task) {
        if(tasks == null) {
            tasks = task;
            return;
        }
        task.setNext(tasks);
        tasks = task;
    }
    
    public static Task removeTask(Task task) {
        if(tasks == null) {
            return null;
        }
        if(tasks == task) {
            tasks = tasks.getNext();
            return tasks;
        }
        
        Task thisTask = tasks.getNext(), previousTask = tasks;
        
        while(thisTask != null) {
            if(thisTask == task) {
                previousTask.setNext(thisTask.getNext());
                return thisTask.getNext();
            } else {
                previousTask = thisTask;
                thisTask = thisTask.getNext();
            }
        }
        return null;
    }
    
    public static void gameTick(float deltaTime) {
        Task thisTask = tasks;
        
        while(thisTask != null) {
            boolean shouldRemove = thisTask.gameTick(deltaTime);
            if(shouldRemove) {
                thisTask = removeTask(thisTask);
            }
        }
    }
}