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
    private int evolutionId;
    private int epochId;
    private int genomeId;
    private Float fitness;
    private List<Float> genomeWeights;

    public Probe() {
    }

    public Probe(int evolutionId, int epochId, int genomeId, Float fitness, List<Float> weights) {
        this.evolutionId = evolutionId;
        this.epochId = epochId;
        this.genomeId = genomeId;
        this.fitness = fitness;
        genomeWeights = weights;
    }

    public ObjectId getId() {
        return id;
    }

    public int getEvolutionId() {
        return evolutionId;
    }

    public int getEpochId() {
        return epochId;
    }

    public int getGenomeId() {
        return genomeId;
    }

    public Float getFitness() {
        return fitness;
    }
    public List<Float> getGenomeWeights() {
        return genomeWeights;
    }
}
