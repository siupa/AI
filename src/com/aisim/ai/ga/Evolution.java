package com.aisim.ai.ga;

import com.aisim.ai.ann.DefaultPerceptronProvider;
import com.aisim.ai.ann.Perceptron;

/**
 * ai
 * Created by Krzysztof Slupski on 8/15/2015.
 */
public class Evolution {

	private final EvolutionConfiguration configuration;

	public Evolution(EvolutionConfiguration configuration) {
		this.configuration = configuration;
	}

	public void run() {

		Population population = new Population(1, configuration.getPopulationConfiguration(), new DefaultPerceptronProvider());
		System.out.print(population.toString());
		for (int i = 0; i < configuration.getEpochsCount(); i++) {
			population.scoreGeneration();
			population.createNewGeneration();
			System.out.print(population.toString());
		}
	}
}
