package com.aisim.dal.model;

import org.joda.time.DateTime;

/**
 * ai
 * Created by Krzysztof on 9/19/2015.
 */
public class LatestEpochInfo {
	private final int evolutionId;
	private final DateTime createdOn;
	private final long epochId;

	public LatestEpochInfo(int evolutionId, DateTime createdOn, long epochId) {
		this.evolutionId = evolutionId;
		this.createdOn = createdOn;
		this.epochId = epochId;
	}

	public int getEvolutionId() {
		return evolutionId;
	}

	public DateTime getCreatedOn() {
		return createdOn;
	}

	public long getEpochId() {
		return epochId;
	}
}
