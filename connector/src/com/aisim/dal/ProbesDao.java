package com.aisim.dal;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Morphia;
import org.mongodb.morphia.dao.BasicDAO;

/**
 * ai
 * Created by Krzysztof Slupski on 8/18/2015.
 */
public class ProbesDao extends BasicDAO {
	public ProbesDao(Morphia morphia, MongoClient mongo) {
		super(mongo, morphia, "ai");
	}
}
