package com.aisim.ai.ann;

/**
 * Created by Krzysztof on 3/3/2015.
 */
public interface PerceptronConfiguration {

    int getInputsCount();

    int getOutputsCount();

    int[] getLayerNeuronsCount();

    float getBias();

    float getSigmoidActivationResponse();

}
