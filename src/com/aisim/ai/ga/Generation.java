package com.aisim.ai.ga;

/**
 * ai
 * Created by Krzysztof Slupski on 8/16/2015.
 */
public class Generation {

	private long id;
	private Population population;

	private Generation(long id, Population population) {
		this.id = id;
		this.population = population;
	}

	public static Generation create(long id, Population population) {
		return new Generation(id, population);
	}

	public Generation live() {
		// this is where the life of one generation happens

		// cross over and mutation
		population.reproduce();
		population.mutate();
		return create(++id, population);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(String.format("Generation %d:\n", this.id));
		builder.append(population.toString());
		return builder.toString();
	}
}
