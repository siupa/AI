package com.aisim.dal.model;

import com.aisim.dal.mongodb.JodaDateTimeConverter;
import org.bson.types.ObjectId;
import org.joda.time.DateTime;
import org.mongodb.morphia.annotations.*;

import java.util.List;

/**
 * ai
 * Created by Krzysztof Slupski on 8/18/2015.
 */
@Entity("probes")
@Indexes(
	@Index(value = "epoch", fields = @Field("evolutionId, epochId"))
)
@Converters(JodaDateTimeConverter.class)
public class Probe {

	@Id
	private final ObjectId id;
	private final int evolutionId;
	@Property
	private final DateTime createdOn;
	private final long epochId;
	private final long genomeId;
	private final Float fitness;

	private List<Float> genomeWeights;

	public Probe() {
		id = null;
		evolutionId = 0;
		createdOn = null;
		epochId = 0;
		genomeId = 0;
		fitness = null;
	}

	public Probe(ObjectId id, int evolutionId, DateTime createdOn, long epochId, long genomeId, Float fitness, List<Float> genomeWeights) {
		this.id = id;
		this.evolutionId = evolutionId;
		this.createdOn = createdOn;
		this.epochId = epochId;
		this.genomeId = genomeId;
		this.fitness = fitness;
		this.genomeWeights = genomeWeights;
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

	public long getGenomeId() {
		return genomeId;
	}

	public Float getFitness() {
		return fitness;
	}

	public List<Float> getGenomeWeights() {
		return genomeWeights;
	}
}
