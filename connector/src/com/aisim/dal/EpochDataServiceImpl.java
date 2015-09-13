package com.aisim.dal;

import com.aisim.ai.ann.DefaultPerceptronProvider;
import com.aisim.ai.ann.PerceptronConfiguration;
import com.aisim.ai.ga.DefaultPopulationConfiguration;
import com.aisim.ai.ga.Epoch;
import com.aisim.ai.ga.Genome;
import com.aisim.ai.ga.Population;
import com.google.common.base.Function;
import com.google.common.collect.FluentIterable;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * ai
 * Created by Krzysztof Slupski on 9/13/2015.
 */
public class EpochDataServiceImpl implements EpochDataService {

	EpochProbesDao dao;
	PerceptronConfiguration perceptronConfiguration;

	public EpochDataServiceImpl(EpochProbesDao dao, PerceptronConfiguration perceptronConfiguration) {
		this.dao = dao;
		this.perceptronConfiguration = perceptronConfiguration;
	}

	@Override
	public void save(int evolutionId, Epoch epoch) throws Exception {
		try {
			Population population = epoch.getPopulation();
			for (Genome g : population.getGenomes()) {
				dao.create(new Probe(ObjectId.get(), evolutionId, epoch.getId(), g.getId(), g.getFitness(), g.getDna()));
			}
		} catch (Exception e) {
			throw new Exception("Saving service failed. Epoch for evolutionId %d and epochId %d hasn't been persisted.", e);
		}
	}

	@Override
	public Epoch loadLatest() throws Exception {
		try {
			List<Probe> probes = dao.retrieveLatest();
			if (probes != null && probes.size() > 0) {
				return Epoch.create(probes.get(0).getEpochId(), new Population(FluentIterable.from(probes).transform(new Function<Probe, Genome>() {
					@Override
					public Genome apply(Probe input) {
						return new Genome(input.getGenomeId(), input.getGenomeWeights(), perceptronConfiguration);
					}
				}).toList(), new DefaultPopulationConfiguration(), new DefaultPerceptronProvider()));
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new Exception("Loading service failed. Latest epoch hasn't been loaded.", e);
		}
	}

	@Override
	public void clearEvolution(int evolutionId) throws Exception {
		dao.clear(evolutionId);
	}
}
