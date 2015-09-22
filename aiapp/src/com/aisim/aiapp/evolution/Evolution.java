package com.aisim.aiapp.evolution;

import com.aisim.ai.ann.DefaultPerceptronProvider;
import com.aisim.ai.ga.Epoch;
import com.aisim.ai.ga.Genome;
import com.aisim.ai.ga.Population;
import com.aisim.aiapp.game.Actor;
import com.aisim.aiapp.game.Brain;
import com.aisim.dal.contracts.EpochDataService;
import com.aisim.dal.model.LatestEpochInfo;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import org.joda.time.DateTime;

import java.util.List;

/**
 * ai
 * Created by Krzysztof Slupski on 8/15/2015.
 */
public class Evolution {
    private int id;
    private DateTime createdOn;
    private Epoch currentEpoch;
    private long currentEpochAge;
    private final EvolutionConfiguration configuration;
    private final EpochDataService dataService;

    public int getId() {
        return id;
    }

    public Epoch getCurrentEpoch() {
        return currentEpoch;
    }

    public long getCurrentEpochAge() {
        return currentEpochAge;
    }

    public Evolution(EvolutionConfiguration configuration, EpochDataService dataService) {
        createdOn = new DateTime();
        this.configuration = configuration;
        this.dataService = dataService;
    }

    public List<Actor> init() {
        Population population = new Population(configuration.getPopulationConfiguration(), new DefaultPerceptronProvider());
        if (configuration.getLoadLatestEpoch())
            try {
                LatestEpochInfo info = dataService.getLatestEpochInfo();
                if (info != null) {
                    id = info.getEvolutionId() + 1;
                    currentEpoch = dataService.load(info.getEvolutionId(), info.getEpochId(), configuration.getPopulationConfiguration());
                }
                if (currentEpoch == null)
                    currentEpoch = Epoch.create(1, population);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        else
            currentEpoch = Epoch.create(1, population);
        currentEpochAge = 0;

        return FluentIterable.from(currentEpoch.getPopulation().getGenomes()).transform(new Function<Genome, Actor>() {
            @Override
            public Actor apply(Genome input) {
                return new Actor(input.getId(), new Brain(input));
            }
        }).toList();
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
                dataService.save(id, createdOn, currentEpoch);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            currentEpoch = currentEpoch.next();
            currentEpochAge = 0;
        }
        return true;
    }
}
