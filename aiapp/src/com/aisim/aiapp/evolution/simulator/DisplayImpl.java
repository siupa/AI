package com.aisim.aiapp.evolution.simulator;

/**
 * ai
 * Created by Krzysztof Slupski on 8/17/2015.
 */
public class DisplayImpl implements Display {
    @Override
    public void out(Object content) {
        System.out.print(content);
    }
}
