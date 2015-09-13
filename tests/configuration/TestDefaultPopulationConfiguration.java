package configuration;

import com.aisim.ai.ann.PerceptronConfiguration;
import com.aisim.ai.ga.PopulationConfiguration;

/**
 * ai
 * Created by Krzysztof on 9/13/2015.
 */
public class TestDefaultPopulationConfiguration implements PopulationConfiguration {
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
        return new TestDefaultPerceptronConfiguration();
    }
}
