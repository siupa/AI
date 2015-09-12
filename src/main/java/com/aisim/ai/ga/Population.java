package com.aisim.ai.ga;

import com.aisim.ai.ann.PerceptronProvider;
import com.scottlogic.util.SortedList;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * ai
 * Created by Krzysztof Slupski on 3/3/2015.
 */
public class Population {

    private final PopulationConfiguration configuration;
    private final PerceptronProvider perceptronProvider;
    private Comparator<Genome> comparator = new Comparator<Genome>() {
        @Override
        public int compare(Genome g1, Genome g2) {
            return g2.getFitness().compareTo(g1.getFitness());
        }
    };
    private List<Genome> chromosoms = new SortedList<Genome>(comparator);


    private Population(List<Genome> chromosoms, PopulationConfiguration configuration, PerceptronProvider perceptronProvider) {
        this.configuration = configuration;
        this.perceptronProvider = perceptronProvider;
        this.chromosoms.addAll(chromosoms);
    }

    public Population(PopulationConfiguration configuration, PerceptronProvider perceptronProvider) {
        this.configuration = configuration;
        this.perceptronProvider = perceptronProvider;
        init();
    }

    public List<Genome> getChromosoms() {
        return chromosoms;
    }

    private void init() {
        for (int i = 0; i < configuration.getChromosomsCount(); i++) {
            chromosoms.add(new Genome(i, perceptronProvider));
        }
    }

    public Population reproduce() {

        List<Genome> newChromosoms = new ArrayList<Genome>();
        List<Genome> elite = selectElite(configuration.getElitePercentage());
        while (newChromosoms.size() < configuration.getChromosomsCount()) {
            Genome mum = roulette(elite);
            Genome dad = roulette(elite);
            Genome child = crossOver(mum, dad);
            mutate(child);
            newChromosoms.add(child);
        }
        return new Population(newChromosoms, configuration, perceptronProvider);
    }

    private List<Genome> selectElite(Float percentage) {
        // TODO: implement elite selection
        return chromosoms.subList(0, (int) Math.ceil((double) chromosoms.size() * (double) percentage));
    }

    private Genome roulette(List<Genome> genomes) {
        // TODO: implement roulette logic here
        return genomes.get(2);
    }

    private Genome crossOver(Genome mum, Genome dad) {
        // TODO: implement crossover
        return mum;
    }

    private void mutate(Genome genome) {
        // TODO: implement mutation
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Genome g : chromosoms) {
            builder.append(g);
        }
        return builder.toString();
    }
}
