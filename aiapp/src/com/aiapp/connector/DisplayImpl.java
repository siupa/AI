package com.aiapp.connector;

import com.aisim.connector.Display;

/**
 * Created by Krzysztof on 8/17/2015.
 */
public class DisplayImpl implements Display {
    @Override
    public void out(Object content) {
        System.out.print(content.toString());
    }
}
