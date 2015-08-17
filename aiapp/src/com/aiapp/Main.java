package com.aiapp;

import com.aiapp.connector.DisplayImpl;
import com.aiapp.connector.LiveSimulatorImpl;
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
