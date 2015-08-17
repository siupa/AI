package com.aisim.ai.ga;

import com.aisim.ai.ann.DefaultPerceptronProvider;
import com.aisim.connector.Display;
import com.aisim.connector.LiveSimulator;

/**
 * ai
 * Created by Krzysztof Slupski on 8/15/2015.
 */
public class Evolution {

	private final EvolutionConfiguration configuration;
	private final Display display;
	private final LiveSimulator simulator;

	public Evolution(EvolutionConfiguration configuration, Display display, LiveSimulator simulator) {
		this.configuration = configuration;
		this.display = display;
		this.simulator = simulator;
	}

	public void run() {

		Population population = new Population(configuration.getPopulationConfiguration(), new DefaultPerceptronProvider());
		Epoch epoch = Epoch.create(1, population, simulator);
		display.out(epoch);
		for (int i = 0; i < configuration.getEpochsCount(); i++) {
			epoch = epoch.live();
			display.out(epoch);
		}
	}
}
