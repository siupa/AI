package com.aisim.ai.ga;

import com.aisim.ai.ann.Perceptron;
import com.aisim.ai.ann.PerceptronProvider;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Krzysztof on 3/3/2015.
 */
public class Population {

    private long id;
    private final PopulationConfiguration configuration;
    private final PerceptronProvider perceptronProvider;
    List<Perceptron> chromosoms;

    public Population(int id, PopulationConfiguration configuration, PerceptronProvider perceptronProvider) {
        this.id = id;
        this.configuration = configuration;
        this.perceptronProvider = perceptronProvider;
        this.chromosoms = new LinkedList<Perceptron>();
        createInitialPopulation();
    }

    private void createInitialPopulation() {
        for (int i = 0; i < configuration.getChromosomsCount(); i++) {
            this.chromosoms.add(perceptronProvider.getPerceptron(configuration.getPerceptronConfiguration()));
        }
        // persist initial generation
    }

    private void createNewGeneration()
    {
        this.id ++;
        // select the fittest for crossover
        // mutate random
        // persist new generation
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("\nPopulation %d:\n", this.id));
        for (Perceptron p : chromosoms) {
            builder.append(p.toString());
        }
        return builder.toString();
    }
}
