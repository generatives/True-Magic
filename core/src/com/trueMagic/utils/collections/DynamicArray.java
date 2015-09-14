/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trueMagic.utils.collections;

/**
 *
 * @author Declan Easton
 * @param <T>
 */
public class DynamicArray<T extends Object> {
    
    private Object[] array;
    private int size;
    
    public DynamicArray() {
        array = new Object[100];
        size = 0;
    }
    
    public void set(int index, T item) {
        if(index < 0) {
            return;
        }
        if(index < array.length) {
            array[index] = item;
        } else {
            expandArrayTo(index + 100);
            set(index, item);
        }
        if(index > size) {
            size = index;
        }
    }
    
    public T get(int index) {
        if(index >= 0 && index < array.length) {
            return (T)array[index];
        } else {
            return null;
        }
    }
    
    public int size() {
        return size;
    }
    
    public void expandArrayTo(int length) {
        Object[] temp = new Object[length];
        for(int i = 0; i < temp.length; i++) {
            if(i < array.length) {
                temp[i] = array[i];
            }
        }
        array = temp;
    }
    
    public void expandArrayBy(int length) {
        expandArrayTo(array.length + length);
    }
}
