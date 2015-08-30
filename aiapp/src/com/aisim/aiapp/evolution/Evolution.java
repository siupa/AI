package com.aisim.aiapp.evolution;

import com.aisim.ai.ann.DefaultPerceptronProvider;
import com.aisim.ai.ga.Epoch;
import com.aisim.ai.ga.Population;
import com.aisim.aiapp.evolution.simulator.Display;
import com.aisim.aiapp.evolution.simulator.LiveSimulator;

/**
 * ai
 * Created by Krzysztof Slupski on 8/15/2015.
 */
public class Evolution {

    private final EvolutionConfiguration configuration;
    private final Display display;
    private final LiveSimulator simulator;

    public Epoch getCurrentEpoch() {
        return currentEpoch;
    }

    public long getCurrentEpochAge() {
        return currentEpochAge;
    }

    private Epoch currentEpoch;
    private long currentEpochAge;

    public Evolution(EvolutionConfiguration configuration, Display display, LiveSimulator simulator) {
        this.configuration = configuration;
        this.display = display;
        this.simulator = simulator;
    }

    public void init() {
        Population population = new Population(configuration.getPopulationConfiguration(), new DefaultPerceptronProvider());
        currentEpoch = Epoch.create(1, population);
        display.out(currentEpoch);
    }

    public void update(float timePerUpdate, long time) {

        // end of the evolution?
        if (currentEpoch.getId() > configuration.getEpochsCount())
            return;

        currentEpochAge++;

        // end of the epoch?
        if (currentEpochAge > configuration.getEpochLengthInTimeUnits()) {
            simulator.simulate(currentEpoch);
            currentEpoch = currentEpoch.next();
            currentEpochAge = 0;
        }
    }
}
