package com.aisim.ai.ga;

import com.aisim.ai.ann.PerceptronConfiguration;

/**
 * ai
 * Created by Krzysztof Slupski on 3/3/2015.
 */
public interface PopulationConfiguration {

    int getChromosomsCount();

    Float getMutationRate();

    Float getCrossOverRate();
    /**
     * How much of the population will be selected for the breeding. Only best genomes are selected.
     * @return Float number between 0 and 1 indicating the percentage.
     */
    Float getElitePercentage();

    PerceptronConfiguration getPerceptronConfiguration();
}
