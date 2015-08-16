package com.aisim.ai.ga;

import com.aisim.ai.ann.Perceptron;
import com.aisim.ai.ann.PerceptronProvider;

import java.util.LinkedList;
import java.util.List;

/**
 * ai
 * Created by Krzysztof Slupski on 3/3/2015.
 */
public class Population {

	private final PopulationConfiguration configuration;
	private final PerceptronProvider perceptronProvider;
	private List<Genome> chromosoms;

	public List<Genome> getChromosoms() {
		return chromosoms;
	}

	public Population(PopulationConfiguration configuration, PerceptronProvider perceptronProvider) {
		this.configuration = configuration;
		this.perceptronProvider = perceptronProvider;
		this.chromosoms = new LinkedList<Genome>();
		init();
	}

	private void init() {
		for (int i = 0; i < configuration.getChromosomsCount(); i++) {
			this.chromosoms.add(new Genome(i, perceptronProvider));
		}
	}

	public void reproduce() {
		// select the fittest
		// cross over
	}

	public void mutate() {
		// randomly mutate
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (Genome g : chromosoms) {
			builder.append(g.toString());
		}
		return builder.toString();
	}
}
