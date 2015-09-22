package com.aisim.aiapp.game;

import com.aisim.ai.ga.Genome;

import java.util.List;

/**
 * ai
 * Created by Krzysztof Slupski on 9/22/2015.
 */
public class Brain {
	private final Genome genome;

	public Brain(Genome genome) {
		this.genome = genome;
	}

	public Float getFitness() {
		return genome.getFitness();
	}

	public Float decide(Float... input) {
		List<Float> output = genome.getPerceptron().calculate(input);
		if (output.size() != 1)
			throw new RuntimeException("Game is built to expect one output from ANN only");
		return output.get(0);
	}

	public void learn(Float fitnessIncrement) {
		genome.incrementFitness(fitnessIncrement);
	}

}
