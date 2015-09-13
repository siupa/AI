package com.aisim.dal;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.ArrayList;
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
	public List<Probe> retrieve(long evolutionId, long epochId) throws Exception {
		return ds.find(Probe.class).filter("evolutionId =", evolutionId).filter("epochId =", epochId).asList();
	}

	@Override
	public List<Probe> retrieveLatest() throws Exception {
		Probe latest = ds.find(Probe.class).order("-_id").limit(1).get();
		if (latest == null)
			return new ArrayList<>();
		return ds.find(Probe.class).filter("evolutionId =", latest.getEvolutionId()).filter("epochId =", latest.getEpochId()).asList();
	}

	@Override
	public void clear(long evolutionId) throws Exception {
		ds.delete(ds.find(Probe.class).filter("evolutionId =", evolutionId));
	}
}


