import com.aisim.dal.Probe;
import com.aisim.dal.ProbesDao;
import com.mongodb.MongoClient;
import org.junit.Test;

import java.util.ArrayList;

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
        repo.save(new Probe(1, 1, 1, 0.34f, new ArrayList<Float>() {
            {
                add(0.5f);
                add(0.15f);
                add(0.54f);
                add(0.512f);
                add(120.522f);
            }
        }));
        repo.save(new Probe(1, 1, 2, 12.34f, new ArrayList<Float>() {
            {
                add(0.23f);
                add(10.15f);
                add(0.512312f);
                add(112320.522f);
            }
        }));

        System.out.println(repo.query().asList());
    }
}
