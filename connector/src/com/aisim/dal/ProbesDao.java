package com.aisim.dal;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.query.Query;

/**
 * ai
 * Created by Krzysztof Slupski on 8/18/2015.
 */
public class ProbesDao {

    private final Morphia morphia;
    private final MongoClient mongo;
    private final Datastore ds;

    public ProbesDao(MongoClient mongo) {
        morphia = new Morphia();
        morphia.map(Probe.class);
        this.mongo = mongo;
        ds = morphia.createDatastore(mongo, "probes");
    }

    public void save(Probe probe) {
        ds.save(probe);
    }

    public Query<Probe> query() {
        return ds.find(Probe.class);
    }


}


