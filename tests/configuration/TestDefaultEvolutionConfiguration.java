package configuration;

import com.aisim.ai.ga.PopulationConfiguration;
import com.aisim.aiapp.evolution.EvolutionConfiguration;

/**
 * ai
 * Created by Krzysztof on 9/13/2015.
 */
public class TestDefaultEvolutionConfiguration implements EvolutionConfiguration {
    @Override
    public int getEpochsCount() {
        return 100;
    }

    @Override
    public long getEpochLengthInTimeUnits() {
        return 3;
    }

    @Override
    public PopulationConfiguration getPopulationConfiguration() {
        return new TestDefaultPopulationConfiguration();
    }

    @Override
    public boolean getLoadLatestEpoch() {
        return true;
    }
}
