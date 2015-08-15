package com.aisim.ai.ann;

import java.util.LinkedList;

/**
 * Created by Krzysztof on 1/5/2015.
 */
public class PerceptronLayer {
    private LinkedList<PerceptronNeuron> neurons;
    private int size;
    private int id;
    private int inputsCount;

    public PerceptronLayer(int id, int size, int inputsCount) {
        this.neurons = new LinkedList<PerceptronNeuron>();
        this.size = size;
        this.id = id;
        this.inputsCount = inputsCount;
        initialize();
    }

    public LinkedList<PerceptronNeuron> getNeurons() {
        return neurons;
    }

    public void initialize() {
        neurons.clear();
        for (int i = 0; i < size; i++) {
            neurons.add(new PerceptronNeuron(inputsCount));
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("(%d):", this.id));
        for (PerceptronNeuron n : neurons) {
            builder.append("[" + n.toString() + "]");
        }
        builder.append("\n");
        return builder.toString();
    }
}