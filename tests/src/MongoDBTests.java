import com.aisim.dal.Probe;
import com.aisim.dal.ProbesDao;
import com.mongodb.MongoClient;
import org.junit.Test;

/**
 * ai
 * Created by Krzysztof Slupski on 8/18/2015.
 */
public class MongoDBTests {

	@Test
	public void connectionTest()

	{
		MongoClient client = new MongoClient();
		ProbesDao repo = new ProbesDao(client);
		repo.save(new Probe());
	}
}
