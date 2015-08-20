package com.aisim.aiapp;

import com.aisim.ai.ga.PopulationConfiguration;

/**
 * ai
 * Created by Krzysztof Slupski on 8/15/2015.
 */
public interface EvolutionConfiguration {
    int getEpochsCount();

    PopulationConfiguration getPopulationConfiguration();
}
