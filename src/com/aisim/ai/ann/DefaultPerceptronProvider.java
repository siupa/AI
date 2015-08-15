package com.aisim.ai.ann;

/**
 * Created by Krzysztof on 3/3/2015.
 */
public class DefaultPerceptronProvider implements PerceptronProvider {
    @Override
    public Perceptron getPerceptron(PerceptronConfiguration configuration) {
        return new Perceptron(configuration);
    }
}
