package com.aisim.ai.ga;

import com.aisim.ai.ann.DefaultPerceptronConfiguration;
import com.aisim.ai.ann.PerceptronConfiguration;

/**
 * ai
 * Created by Krzysztof Slupski on 8/15/2015.
 */
public class DefaultPopulationConfiguration implements PopulationConfiguration {
    @Override
    public int getChromosomsCount() {
        return 10;
    }

    @Override
    public Float getMutationRate() {
        return .1f;
    }

    @Override
    public Float getCrossOverRate() {
        return 0.7f;
    }

    @Override
    public Float getElitePercentage() {
        return .3f;
    }

    @Override
    public PerceptronConfiguration getPerceptronConfiguration() {
        return new DefaultPerceptronConfiguration();
    }
}
