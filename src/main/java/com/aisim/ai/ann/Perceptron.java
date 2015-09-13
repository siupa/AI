package com.aisim.ai.ann;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * ai
 * Created by Krzysztof Slupski on 1/5/2015.
 */
public class Perceptron {
    private final PerceptronConfiguration configuration;
    private final List<PerceptronLayer> layers;

    public Perceptron(PerceptronConfiguration configuration) {
        this.configuration = configuration;
        layers = new ArrayList<>();
        init();
    }

    public PerceptronConfiguration getConfiguration() {
        return configuration;
    }

    private void init() {
        layers.clear();
        if (configuration.getLayerNeuronsCount().length > 0) {
            for (int i = 0; i < configuration.getLayerNeuronsCount().length; i++) {
                layers.add(new PerceptronLayer(i, configuration.getLayerNeuronsCount()[i],
                        /* first layer accepts initial inputs*/
                        i == 0 ? configuration.getInputsCount() : configuration.getLayerNeuronsCount()[i - 1]));
                // last layer is output layer
                if (i == configuration.getLayerNeuronsCount().length - 1)
                    layers.add(new PerceptronLayer(i + 1, configuration.getOutputsCount(), configuration.getLayerNeuronsCount()[i]));
            }
        } else {
            layers.add(new PerceptronLayer(0, configuration.getOutputsCount(), configuration.getInputsCount()));
        }
    }

//    private class NeuronRunnable implements Runnable {
//        private Float[] input;
//        private PerceptronNeuron neuron;
//
//        private NeuronRunnable(Float[] input, PerceptronNeuron neuron) {
//            this.input = input;
//            this.neuron = neuron;
//        }
//
//        @Override
//        public void run() {
//            float netinput = 0;
//            int weightCount = 0;
//            for (Float w : neuron.getWeights())
//                netinput += w * input[weightCount++];
//            netinput += neuron.getWeights().getLast() * configuration.getBias();
//            System.out.println("\nRunnable: " + netinput);
//        }
//    }

    public LinkedList<Float> calculate(Float... input) {
        LinkedList<Float> output = new LinkedList<>();
        for (PerceptronLayer l : layers) {
            output.clear();
            int weightCount = 0;
            for (PerceptronNeuron n : l.getNeurons()) {

//                ExecutorService service = Executors.newFixedThreadPool(5);
//                service.submit(new NeuronRunnable(input, n));
                float netinput = 0;
                for (Float w : n.getWeights())
                    netinput += w * input[weightCount++];

                netinput += n.getWeights().getLast() * configuration.getBias();
                output.add(sigmoid(netinput));
                weightCount = 0;
            }
            input = output.<Float>toArray(input);
        }
        return output;
    }

    public List<Float> flatten() {
        List<Float> weights = new ArrayList<>();
        for (PerceptronLayer l : layers) {
            for (PerceptronNeuron n : l.getNeurons()) {
                weights.addAll(n.getWeights());
            }
        }
        return weights;
    }

    public void restore(List<Float> weights) {
        int weight = 0;
        for (PerceptronLayer layer : layers)
            for (int j = 0; j < layer.getNeurons().size(); ++j)
                for (int k = 0; k < layer.getNeurons().get(j).getWeights().size(); ++k)
                    layer.getNeurons().get(j).getWeights().set(k, weights.get(weight++));
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (PerceptronLayer l : layers) {
            builder.append(l).append("\n");
        }
        return builder.toString();
    }

    private float sigmoid(float input) {
        return (float) (1 / (1 + Math.exp(-input / configuration.getSigmoidActivationResponse())));
    }
}
