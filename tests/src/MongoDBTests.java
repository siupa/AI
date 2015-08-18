import com.aisim.dal.Probe;
import com.aisim.dal.ProbesRepository;
import com.mongodb.Block;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.Document;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * ai
 * Created by Krzysztof Slupski on 8/18/2015.
 */
public class MongoDBTests {

	@Test
	public void connectionTest()

	{
		MongoClient client = new MongoClient();
		ProbesRepository repo = new ProbesRepository(client);
		repo.saveProbe(new Probe());
	}
}
