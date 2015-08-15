package com.aisim.ai.ann;

/**
 * Created by Krzysztof on 3/3/2015.
 */
public class DefaultPerceptronConfiguration implements PerceptronConfiguration {
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
