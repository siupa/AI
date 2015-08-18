package com.aisim.aipp;

import com.aisim.aipp.connector.DisplayImpl;
import com.aisim.aipp.connector.LiveSimulatorImpl;
import com.aisim.ai.ga.DefaultEvolutionConfiguration;
import com.aisim.ai.ga.Evolution;

public class Main {
    public static void main(String[] args) {
        Evolution evolution = new Evolution(
                new DefaultEvolutionConfiguration(),
                new DisplayImpl(),
                new LiveSimulatorImpl());
        evolution.run();
    }
}
