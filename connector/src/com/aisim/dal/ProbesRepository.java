package com.aisim.dal;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.List;

/**
 * ai
 * Created by Krzysztof Slupski on 8/18/2015.
 */
public class ProbesRepository {

	private Morphia morphia;
	private MongoClient mongo;
	private Datastore ds;

	public ProbesRepository(MongoClient mongo) {
		this.morphia = new Morphia();
		morphia.map(Probe.class);
		this.mongo = mongo;
		this.ds = morphia.createDatastore(mongo, "probes");
	}

	public void saveProbe(Probe probe)
	{
		ProbesDao dao = new ProbesDao(morphia, mongo);
		dao.save(probe);
	}
}


