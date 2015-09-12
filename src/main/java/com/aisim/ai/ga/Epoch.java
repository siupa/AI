package com.aisim.ai.ga;

/**
 * ai
 * Created by Krzysztof Slupski on 8/16/2015.
 */
public class Epoch {

    private long id;
    private Population population;

    public long getId() {
        return id;
    }

    public Population getPopulation() {
        return population;
    }

    private Epoch(long id, Population population) {
        this.id = id;
        this.population = population;
    }

    public static Epoch create(long id, Population population) {
        return new Epoch(id, population);
    }

    public Epoch next() {
        calculateFitness();
        scaleFitness();
        return create(id + 1, population.reproduce());
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
        builder.append(String.format("Generation %d:\n", id));
        builder.append(population);
        return builder.toString();
    }


}
