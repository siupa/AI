package com.aisim.ai.ga;

import com.aisim.ai.ann.Perceptron;
import com.aisim.ai.ann.PerceptronConfiguration;
import com.aisim.ai.ann.PerceptronProvider;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.util.List;

/**
 * ai
 * Created by Krzysztof Slupski on 3/5/2015.
 */
public class Genome {
    private long id;
    private Float fitness;
    private int fitnessPoints;
    private List<Float> dna;
    private Perceptron perceptron;

    public Genome(long id, PerceptronProvider perceptronProvider) {
        this.id = id;
        this.fitness = 0.0f;
        this.fitnessPoints = 0;
        this.perceptron = perceptronProvider.getPerceptron();
        this.dna = perceptron.flatten();
    }

    public Genome(long id, List<Float> dna, PerceptronConfiguration perceptronConfiguration) {
        this.id = id;
        this.fitness = 0.0f;
        this.fitnessPoints = 0;
        this.dna = dna;
        this.perceptron = new Perceptron(perceptronConfiguration);
        this.perceptron.restore(this.dna);
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

    @Override
    public int hashCode() {
        return new HashCodeBuilder().
            append(id).
            append(fitness).
            append(dna.toArray()).
            toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Genome))
            return false;
        if (obj == this)
            return true;

        Genome rhs = (Genome) obj;
        return new EqualsBuilder().
            append(id, rhs.id).
            append(fitness, rhs.fitness).
            append(dna.toArray(), rhs.dna.toArray()).
            isEquals();
    }
}
