package com.ezdi.pbac.perf.generators;

import java.util.Random;

/**
 * Created by EZDI\ganesh.s on 20/9/16.
 */
public class ListGenerator<T> implements Generator {
    private T[] list;
    private Random random;

    public ListGenerator(T[] list) {
        this.list = list;
        this.random = new Random();
    }

    @Override
    public Object generate() {
        return list[random.nextInt(list.length)];
    }
}
