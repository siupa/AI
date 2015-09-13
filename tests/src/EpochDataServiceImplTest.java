import com.aisim.ai.ann.DefaultPerceptronConfiguration;
import com.aisim.ai.ann.DefaultPerceptronProvider;
import com.aisim.ai.ga.DefaultPopulationConfiguration;
import com.aisim.ai.ga.Epoch;
import com.aisim.ai.ga.Population;
import com.aisim.dal.EpochDataService;
import com.aisim.dal.EpochDataServiceImpl;
import com.aisim.dal.EpochProbesMongodbDao;
import com.mongodb.MongoClient;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * ai 
 * Created by Krzysztof Slupski on 9/13/2015.
 */
public class EpochDataServiceImplTest {
    private final int TEST_EVOLUTION_ID = -999;
    private final int TEST_EPOCH_ID = -999;
    EpochDataService service;

    public EpochDataServiceImplTest() {
        service = new EpochDataServiceImpl(new EpochProbesMongodbDao(new MongoClient()), new DefaultPerceptronConfiguration());
    }

    @Test
    public void testSaveAndLoad() throws Exception {
        service.save(TEST_EVOLUTION_ID, Epoch.create(TEST_EPOCH_ID, new Population(new DefaultPopulationConfiguration(), new DefaultPerceptronProvider())));
        Epoch epoch = service.loadLatest();
        assertTrue(epoch.getId() == TEST_EPOCH_ID);
        assertTrue(epoch.getPopulation().getGenomes().size() > 0);
    }

    @After
    public void tearDown() throws Exception {
        service.clearEvolution(TEST_EVOLUTION_ID);
    }
}
