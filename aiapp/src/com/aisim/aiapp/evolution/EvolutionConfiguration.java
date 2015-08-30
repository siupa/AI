package com.aisim.aiapp.evolution;

import com.aisim.ai.ga.PopulationConfiguration;

/**
 * ai
 * Created by Krzysztof Slupski on 8/15/2015.
 */
public interface EvolutionConfiguration {
    int getEpochsCount();
    long getEpochLengthInTimeUnits();

    PopulationConfiguration getPopulationConfiguration();
}
