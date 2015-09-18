package com.aisim.aiapp.evolution;

import com.aisim.ai.ann.DefaultPerceptronProvider;
import com.aisim.ai.ga.Epoch;
import com.aisim.ai.ga.Population;
import com.aisim.dal.EpochDataService;

/**
 * ai
 * Created by Krzysztof Slupski on 8/15/2015.
 */
public class Evolution {

    private final EvolutionConfiguration configuration;
    private final EpochDataService dataService;

    public Epoch getCurrentEpoch() {
        return currentEpoch;
    }

    public long getCurrentEpochAge() {
        return currentEpochAge;
    }

    private Epoch currentEpoch;
    private long currentEpochAge;

    public Evolution(EvolutionConfiguration configuration, EpochDataService dataService) {
        this.configuration = configuration;
        this.dataService = dataService;
    }

    public int getId()
    {
        return hashCode();
    }

    public void init() {
        Population population = new Population(configuration.getPopulationConfiguration(), new DefaultPerceptronProvider());
        if (configuration.getLoadLatestEpoch())
            try {
                currentEpoch = dataService.loadLatest();
                if(currentEpoch == null)
                    currentEpoch = Epoch.create(1, population);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        else
            currentEpoch = Epoch.create(1, population);
        currentEpochAge = 0;
    }

    public boolean update() {

        if (currentEpoch == null)
            throw new RuntimeException("Evolution is not initialized. Run init() first.");
        // end of the evolution?
        if (currentEpoch.getId() > configuration.getEpochsCount())
            return false;

        currentEpochAge++;

        // end of the epoch?
        if (currentEpochAge >= configuration.getEpochLengthInTimeUnits()) {
            try {
                dataService.save(getId(), currentEpoch);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            currentEpoch = currentEpoch.next();
            currentEpochAge = 0;
        }
        return true;
    }
}
