package com.aisim.ai.ga;

import com.aisim.ai.ann.PerceptronConfiguration;

/**
 * ai
 * Created by Krzysztof Slupski on 8/15/2015.
 */
public class DefaultEvolutionConfiguration implements EvolutionConfiguration {
	@Override
	public int getEpochsCount() {
		return 100;
	}

	@Override
	public PopulationConfiguration getPopulationConfiguration() {
		return new DefaultPopulationConfiguration();
	}
}
