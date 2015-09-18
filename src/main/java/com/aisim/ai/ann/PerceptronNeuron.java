package com.aisim.ai.ann;

import com.aisim.ai.utils.Helpers;

import java.util.LinkedList;

/**
 * ai
 * Created by Krzysztof Slupski on 1/5/2015.
 */
public class PerceptronNeuron {

    private LinkedList<Float> weights;

    public PerceptronNeuron(int inputsCount) {
        weights = new LinkedList<>();
        for (int i = 0; i < inputsCount; i++) {
            weights.add(Helpers.randomClamped());
        }
    }

    public LinkedList<Float> getWeights() {
        return weights;
    }

    public void setWeights(LinkedList<Float> weights) {
        this.weights = weights;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Float w : weights) {
            builder.append(w).append(",");
        }
        builder.replace(builder.length() - 1, builder.length(), "");
        return builder.toString();
    }

}
