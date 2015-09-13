package com.aisim.dal;

import org.bson.types.ObjectId;
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
public class Probe {

    @Id
    private ObjectId id;
    private long evolutionId;
    private long epochId;
    private long genomeId;
    private Float fitness;

    private List<Float> genomeWeights;

    public Probe() {
    }

    public Probe(ObjectId id, long evolutionId, long epochId, long genomeId, Float fitness, List<Float> weights) {
        this.id = id;
        this.evolutionId = evolutionId;
        this.epochId = epochId;
        this.genomeId = genomeId;
        this.fitness = fitness;
        genomeWeights = weights;
    }

    public ObjectId getId() {
        return id;
    }
    public long getEvolutionId() {
        return evolutionId;
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
