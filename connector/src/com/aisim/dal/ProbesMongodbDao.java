package com.aisim.dal;

import com.aisim.ai.connector.ProbesService;
import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

import java.util.List;

/**
 * ai
 * Created by Krzysztof Slupski on 8/18/2015.
 */
public class ProbesMongodbDao implements ProbesService {

	private final Morphia morphia;
	private final MongoClient mongo;
	private final Datastore ds;

	public ProbesMongodbDao(MongoClient mongo) {
		morphia = new Morphia();
		morphia.map(Probe.class);
		this.mongo = mongo;
		ds = morphia.createDatastore(mongo, "probes");
	}

	@Override
	public void save(int evolutionId, int epochId, int genomeId, Float fitness, List<Float> weights) {
		save(new Probe(evolutionId, epochId, genomeId, fitness, weights));
	}

	public void save(Probe probe) {
		ds.save(probe);
	}

	public Query<Probe> query() {
		return ds.find(Probe.class);
	}

	public void remove(Query<Probe> query) {
		ds.delete(query);
	}


}


