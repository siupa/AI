package com.aisim.dal.model;
import java.util.List;

/**
 * ai
 * Created by Krzysztof Slupski on 9/13/2015.
 */
public interface EpochProbesDao {
	void create(Probe probe) throws Exception;

	/**
	 * @return Latest Epoch with evolutionId, createdOn and epochId. If no data was stored previously, returns null.
	 * @throws Exception
	 */
	LatestEpochInfo getLatestEpochInfo() throws Exception;
	List<Probe> retrieve(int evolutionId, long epochId) throws Exception;

	/**
	 * Removes all the data for the given evolutionId.
	 * @param evolutionId Id of the evolution to be cleared from the storage.
	 * @throws Exception
	 */
	void clear(int evolutionId) throws Exception;
}
