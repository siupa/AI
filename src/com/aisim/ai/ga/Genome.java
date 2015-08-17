package com.aisim.ai.ga;

import com.aisim.ai.ann.Perceptron;
import com.aisim.ai.ann.PerceptronProvider;

import java.util.Collections;
import java.util.Comparator;
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
		this.fitness = 0.0f;
		this.perceptron = perceptronProvider.getPerceptron();
		this.dna = perceptron.flatten();
	}

	public long getId() {
		return id;
	}

	public List<Float> getDna() {
		return dna;
	}

	public void setDna(List<Float> dna) {
		this.dna = dna;
		this.perceptron.restore(dna);
	}

	public Float getFitness() {
		return fitness;
	}

	public void incrementFitness(Float fitnessIncrement) {
		this.fitness += fitnessIncrement;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(String.format("Genome %d [Fitness: %f]:\n", this.id, this.fitness));
		builder.append(perceptron.toString());

		builder.append("DNA:");
		for (Float w : dna) {
			builder.append(w.toString()).append(",");
		}
		builder.replace(builder.length() - 1, builder.length(), "");
		builder.append("\n");
		return builder.toString();
	}
}
