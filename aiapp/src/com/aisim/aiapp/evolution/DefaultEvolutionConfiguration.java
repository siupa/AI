package com.aisim.aiapp.evolution;

import com.aisim.ai.ga.DefaultPopulationConfiguration;
import com.aisim.ai.ga.PopulationConfiguration;

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
    public long getEpochLengthInTimeUnits() {
        return 10;
    }

    @Override
    public PopulationConfiguration getPopulationConfiguration() {
        return new DefaultPopulationConfiguration();
    }
}
