package com.aisim.ai.ga;

import com.aisim.connector.LiveSimulator;

/**
 * ai
 * Created by Krzysztof Slupski on 8/16/2015.
 */
public class Epoch {

    private long id;
    private Population population;
    private final LiveSimulator simulator;
    private Float totalFitness;

    private Epoch(long id, Population population, LiveSimulator simulator) {
        this.id = id;
        this.population = population;
        this.simulator = simulator;
    }

    public static Epoch create(long id, Population population, LiveSimulator simulator) {
        return new Epoch(id, population, simulator);
    }

    public Epoch live() {
        simulator.simulate(population);
        // TODO: calculate total fitness and scale genome finesses accordingly
        calculateFitness();
        scaleFitness();
        return create(id + 1, population.reproduce(), simulator);
    }

    private void scaleFitness() {
        // TODO: implement scaleFitness
    }

    private void calculateFitness() {
        // TODO: implement calculateFitness
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Generation %d:\n", this.id));
        builder.append(population.toString());
        return builder.toString();
    }
}
