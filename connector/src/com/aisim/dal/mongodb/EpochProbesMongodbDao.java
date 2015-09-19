package com.aisim.dal.mongodb;

import com.aisim.dal.model.EpochProbesDao;
import com.aisim.dal.model.LatestEpochInfo;
import com.aisim.dal.model.Probe;
import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.List;

/**
 * ai
 * Created by Krzysztof Slupski on 8/18/2015.
 */
public class EpochProbesMongodbDao implements EpochProbesDao {

    private final Datastore ds;

    public EpochProbesMongodbDao(MongoClient mongo) {
        Morphia morphia = new Morphia();
        morphia.map(Probe.class);
        ds = morphia.createDatastore(mongo, "probes");
    }

    @Override
    public void create(Probe probe) {
        ds.save(probe);
    }

    @Override
    public LatestEpochInfo getLatestEpochInfo() throws Exception {
        Probe latest = ds.find(Probe.class).order("-evolutionId, -epochId").limit(1).get();
        if (latest == null)
            return null;
        return new LatestEpochInfo(latest.getEvolutionId(), latest.getCreatedOn(), latest.getEpochId());
    }

    @Override
    public List<Probe> retrieve(int evolutionId, long epochId) throws Exception {
        return ds.find(Probe.class).filter("evolutionId =", evolutionId).filter("epochId =", epochId).asList();
    }

    @Override
    public void clear(int evolutionId) throws Exception {
        ds.delete(ds.find(Probe.class).filter("evolutionId =", evolutionId));
    }
}


