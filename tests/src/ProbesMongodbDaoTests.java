import com.aisim.dal.Probe;
import com.aisim.dal.ProbesMongodbDao;
import com.mongodb.MongoClient;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;

/**
 * ai
 * Created by Krzysztof Slupski on 8/18/2015.
 */
public class ProbesMongodbDaoTests {
	private final int TEST_EVOLUTION_ID = -999;
	private ProbesMongodbDao repo;

	public ProbesMongodbDaoTests() {
		MongoClient client = new MongoClient();
		repo = new ProbesMongodbDao(client);
	}

	@Test
	public void saveTest() {
		repo.save(TEST_EVOLUTION_ID, 1, 1, 0.1f, new ArrayList<Float>() {
			{
				add(0.5f);
				add(1f);
			}
		});
		repo.save(new Probe(TEST_EVOLUTION_ID, 1, 2, 0.34f, new ArrayList<Float>() {
			{
				add(0.5f);
				add(0.15f);
				add(0.54f);
				add(0.512f);
				add(120.522f);
			}
		}));
		repo.save(new Probe(TEST_EVOLUTION_ID, 1, 3, 12.34f, new ArrayList<Float>() {
			{
				add(0.23f);
				add(10.15f);
				add(0.512312f);
				add(112320.522f);
			}
		}));
		assertTrue(repo.query().asList().size() == 3);
	}

	@After
	public void tearDown() {
		repo.remove(repo.query().filter("evolutionId =", TEST_EVOLUTION_ID));
	}
}
