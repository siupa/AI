package com.aisim.ai.ann;

/**
 * ai
 * Created by Krzysztof Slupski on 3/3/2015.
 */
public class DefaultPerceptronProvider implements PerceptronProvider {
    @Override
    public Perceptron getPerceptron() {
        return new Perceptron(new DefaultPerceptronConfiguration());
    }
}
