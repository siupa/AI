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

		Population population = new Population(configuration.getPopulationConfiguration(), new DefaultPerceptronProvider());
		Generation generation = Generation.create(1, population);
		System.out.print(generation.toString());
		for (int i = 0; i < configuration.getEpochsCount(); i++) {
			generation = generation.live();
			System.out.print(generation.toString());
		}
	}
}
