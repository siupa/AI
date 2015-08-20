package com.aisim.ai.ga;

import com.aisim.ai.ann.Perceptron;
import com.aisim.ai.ann.PerceptronProvider;

import java.util.List;

/**
 * ai
 * Created by Krzysztof Slupski on 3/5/2015.
 */
public class Genome {
    private long id;
    private Float fitness;
    private List<Float> dna;
    private Perceptron perceptron;

    public Genome(long id, PerceptronProvider perceptronProvider) {
        this.id = id;
        fitness = 0.0f;
        perceptron = perceptronProvider.getPerceptron();
        dna = perceptron.flatten();
    }

    public Genome(long id, List<Float> dna) {
        this.id = id;
        fitness = 0.0f;
        this.dna = dna;
        perceptron.restore(this.dna);
    }

    public long getId() {
        return id;
    }

    public List<Float> getDna() {
        return dna;
    }

    public void setDna(List<Float> dna) {
        this.dna = dna;
        perceptron.restore(dna);
    }

    public Float getFitness() {
        return fitness;
    }

    public void incrementFitness(Float fitnessIncrement) {
        fitness += fitnessIncrement;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Genome %d [Fitness: %f]:\n", id, fitness));
        builder.append(perceptron);

        builder.append("DNA:");
        for (Float w : dna) {
            builder.append(w).append(",");
        }
        builder.replace(builder.length() - 1, builder.length(), "");
        builder.append("\n");
        return builder.toString();
    }
}
