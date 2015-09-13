package com.aisim.dal;
import java.util.List;

/**
 * ai
 * Created by Krzysztof Slupski on 9/13/2015.
 */
public interface EpochProbesDao {
	public void create(Probe probe) throws Exception;
	public List<Probe> retrieve(long evolutionId, long epochId) throws Exception;
	public List<Probe> retrieveLatest() throws Exception;
	public void clear(long evolutionId) throws Exception;
}
