/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.trueMagic.utils.collections;

import java.util.Iterator;

/**
 *
 * @author Declan Easton
 */
public class MultiIterator<T> implements Iterator {
    
    Iterator<T>[] iters;
    int iterNum;
    
    public MultiIterator(Iterator<T>[] iters) {
        this.iters = iters;
        iterNum = 0;
    }

    @Override
    public boolean hasNext() {
        for(int i = iterNum; i < iters.length; i++) {
            boolean hasNext = iters[i].hasNext();
            if(hasNext) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object next() {
        for(; iterNum < iters.length; iterNum++) {
            Object next = iters[iterNum].next();
            if(next != null) {
                return next;
            }
        }
        return null;
    }
    
}
