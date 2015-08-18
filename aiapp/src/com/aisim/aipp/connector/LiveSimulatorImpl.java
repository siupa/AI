package com.aisim.aipp.connector;

import com.aisim.ai.ga.Genome;
import com.aisim.ai.ga.Population;
import com.aisim.connector.LiveSimulator;

import java.util.Random;

/**
 * ai
 * Created by Krzysztof Slupski on 8/17/2015.
 */
public class LiveSimulatorImpl implements LiveSimulator {
    private static final Random random = new Random();

    @Override
    public void simulate(Population population) {
        for (int i = 0; i < population.getChromosoms().size(); i++) {
            Genome g = population.getChromosoms().get(i);
            // TODO: implement here some logic that would score the population accordingly
            g.incrementFitness(random.nextFloat() - 0.5f);
        }
    }
}
