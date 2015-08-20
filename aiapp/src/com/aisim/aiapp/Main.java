package com.aisim.aiapp;

public class Main {
    public static void main(String[] args) {
        Evolution evolution = new Evolution(
                new DefaultEvolutionConfiguration(),
                new DisplayImpl(),
                new LiveSimulatorImpl());
        evolution.run();
    }
}
