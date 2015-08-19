package com.aisim.dal;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import java.util.List;

/**
 * ai
 * Created by Krzysztof Slupski on 8/18/2015.
 */
public class ProbesDao {

	private Morphia morphia;
	private MongoClient mongo;
	private Datastore ds;

	public ProbesDao(MongoClient mongo) {
		this.morphia = new Morphia();
		morphia.map(Probe.class);
		this.mongo = mongo;
		this.ds = morphia.createDatastore(mongo, "probes");
	}

	public void save(Probe probe)
	{
		this.ds.save(probe);
	}

	public List<Probe> getAll(Class clazz)
	{
		return this.ds.find(clazz).asList();
	}


}


