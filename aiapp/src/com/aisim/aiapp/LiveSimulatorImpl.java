package com.aisim.aiapp;

import com.aisim.ai.ga.Epoch;
import com.aisim.ai.ga.Genome;

import java.util.Random;

/**
 * ai
 * Created by Krzysztof Slupski on 8/17/2015.
 */
public class LiveSimulatorImpl implements LiveSimulator {
    private static final Random random = new Random();

    @Override
    public void simulate(Epoch epoch) {
        for (int i = 0; i < epoch.getPopulation().getChromosoms().size(); i++) {
            Genome g = epoch.getPopulation().getChromosoms().get(i);
            // TODO: implement here some logic that would score the population accordingly
            g.incrementFitness(random.nextFloat() - 0.5f);
        }
    }
}
