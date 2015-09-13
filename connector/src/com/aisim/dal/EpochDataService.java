package com.aisim.dal;
import com.aisim.ai.ga.Epoch;

/**
 * ai
 * Created by Krzysztof Slupski on 9/12/2015.
 */
public interface EpochDataService {
	void save(int evolutionId, Epoch epoch) throws Exception;
	Epoch loadLatest() throws Exception;
	void clearEvolution(int evolutionId) throws Exception;
}
