package dao;

import com.aisim.ai.ann.DefaultPerceptronProvider;
import com.aisim.ai.ga.Epoch;
import com.aisim.ai.ga.Population;
import com.aisim.ai.ga.PopulationConfiguration;
import com.aisim.dal.EpochDataServiceImpl;
import com.aisim.dal.contracts.EpochDataService;
import com.aisim.dal.mongodb.EpochProbesMongodbDao;
import com.mongodb.MongoClient;
import configuration.TestDefaultPopulationConfiguration;
import org.joda.time.DateTime;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * ai
 * Created by Krzysztof Slupski on 9/13/2015.
 */
public class EpochDataServiceImplTest {
    private final int TEST_EVOLUTION_ID = -999;
    private final int TEST_EPOCH_ID = -999;
    EpochDataService service;

    public EpochDataServiceImplTest() {
        service = new EpochDataServiceImpl(new EpochProbesMongodbDao(new MongoClient()));
    }

    @Test
    public void testSaveAndLoad() throws Exception {
        PopulationConfiguration configuration = new TestDefaultPopulationConfiguration();
        service.save(TEST_EVOLUTION_ID, DateTime.now(), Epoch.create(TEST_EPOCH_ID, new Population(configuration, new DefaultPerceptronProvider())));
        Epoch epoch = service.load(TEST_EVOLUTION_ID, TEST_EPOCH_ID, configuration);
        assertEquals(String.format("Epoch is retrieved with id %d", TEST_EPOCH_ID), TEST_EPOCH_ID, epoch.getId());
        assertNotEquals("Epoch population has some genomes", epoch.getPopulation().getGenomes().size(), 0);
    }

    @After
    public void tearDown() throws Exception {
        service.clearEvolution(TEST_EVOLUTION_ID);
    }
}
