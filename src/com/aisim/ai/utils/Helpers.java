package com.aisim.ai.utils;

import java.lang.Math;
import java.util.Random;

/**
 * Created by Krzysztof on 2/25/2015.
 */
public class Helpers {

    private static double PRECISION_FACTOR = Math.pow(10, 2);

    public static float randomClamped()
    {
        return truncate((new Random().nextFloat() - 0.5f) * 2);
    }

    private static float truncate(float number) {
        return (float) (Math.round(number * PRECISION_FACTOR) / PRECISION_FACTOR);
    }
}
