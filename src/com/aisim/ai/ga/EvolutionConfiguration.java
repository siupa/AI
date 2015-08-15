package com.aisim.ai.ga;

import com.aisim.ai.ann.PerceptronConfiguration;

/**
 * ai
 * Created by Krzysztof Slupski on 8/15/2015.
 */
public interface EvolutionConfiguration {
	int getEpochsCount();
	PopulationConfiguration getPopulationConfiguration();
}
