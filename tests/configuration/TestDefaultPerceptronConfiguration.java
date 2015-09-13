package configuration;

import com.aisim.ai.ann.PerceptronConfiguration;

/**
 * ai
 * Created by Krzysztof on 9/13/2015.
 */
public class TestDefaultPerceptronConfiguration implements PerceptronConfiguration {
    @Override
    public int getInputsCount() {
        return 3;
    }

    @Override
    public int getOutputsCount() {
        return 1;
    }

    @Override
    public int[] getLayerNeuronsCount() {
        return new int[]{13};
    }

    @Override
    public float getBias() {
        return -1.0f;
    }

    @Override
    public float getSigmoidActivationResponse() {
        return 1.0f;
    }
}
