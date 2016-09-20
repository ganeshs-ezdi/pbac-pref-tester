package com.ezdi.pbac.perf.generators;

import java.util.Random;

/**
 * Created by EZDI\ganesh.s on 20/9/16.
 */
public class IntegerGenerator implements Generator {
    private int min;
    private int max;
    private int diff;
    private Random random;

    public IntegerGenerator(int min, int max) {
        this.min = min;
        this.max = max;
        this.diff = max-min;
        this.random = new Random();
    }

    @Override
    public Object generate() {
        int n = random.nextInt(diff);
        return min + n;
    }
}
