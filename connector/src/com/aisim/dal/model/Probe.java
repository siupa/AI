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
    private ObjectId id;
    private int evolutionId;
    @Property
    private DateTime createdOn;
    private long epochId;
    private long genomeId;
    private Float fitness;

    private List<Float> genomeWeights;

    public Probe() {
    }

    public Probe(ObjectId id, int evolutionId, DateTime createdOn, long epochId, long genomeId, Float fitness, List<Float> weights) {
        this.id = id;
        this.evolutionId = evolutionId;
        this.createdOn = createdOn;
        this.epochId = epochId;
        this.genomeId = genomeId;
        this.fitness = fitness;
        genomeWeights = weights;
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
