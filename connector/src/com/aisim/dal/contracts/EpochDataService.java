package com.aisim.dal.contracts;

import com.aisim.ai.ga.Epoch;
import com.aisim.ai.ga.PopulationConfiguration;
import com.aisim.dal.model.LatestEpochInfo;
import org.joda.time.DateTime;

/**
 * ai
 * Created by Krzysztof Slupski on 9/12/2015.
 */
public interface EpochDataService {
	void save(int evolutionId, DateTime createdOn, Epoch epoch) throws Exception;

	LatestEpochInfo getLatestEpochInfo() throws Exception;

	Epoch load(int evolutionId, long epochId, PopulationConfiguration configuration) throws Exception;

	void clearEvolution(int evolutionId) throws Exception;

}
