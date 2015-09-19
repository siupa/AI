package com.aisim.dal.model;
import com.aisim.ai.ga.Epoch;
import org.joda.time.DateTime;

/**
 * ai
 * Created by Krzysztof Slupski on 9/12/2015.
 */
public interface EpochDataService {
	void save(int evolutionId, DateTime createdOn, Epoch epoch) throws Exception;
	LatestEpochInfo getLatestEpochInfo() throws Exception;
	Epoch load(int evolutionId, long epochId) throws Exception;
	void clearEvolution(int evolutionId) throws Exception;

}
