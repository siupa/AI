package com.aisim.ai.ga;

import com.aisim.ai.ann.PerceptronConfiguration;

/**
 * Created by Krzysztof on 3/3/2015.
 */
public interface PopulationConfiguration {

    int getChromosomsCount();

    PerceptronConfiguration getPerceptronConfiguration();
}
