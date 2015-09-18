package aitests;

import com.aisim.aiapp.evolution.DefaultEvolutionConfiguration;
import com.aisim.aiapp.evolution.Evolution;
import com.aisim.dal.EpochDataService;
import com.aisim.dal.EpochDataServiceImpl;
import com.aisim.dal.EpochProbesSqlLiteDao;
import configuration.TestDefaultEvolutionConfiguration;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 * ai
 * Created by Krzysztof Slupski on 3/3/2015.
 */
public class EvolutionTests {

    Evolution evolution;
    EpochDataService dataService;

    public EvolutionTests() {
        dataService = new EpochDataServiceImpl(new EpochProbesSqlLiteDao("../database"),
                new TestDefaultEvolutionConfiguration().getPopulationConfiguration().getPerceptronConfiguration());
        evolution = new Evolution(
                new TestDefaultEvolutionConfiguration(), dataService);
    }

    @Test
    public void evolutionInitTest() {
        evolution.init();
        assertTrue(evolution.getCurrentEpochAge() == 0);
        assertTrue(evolution.getCurrentEpoch().getId() == 1);
    }

    @Test
    public void evolutionUpdateTest() throws Exception {
        evolution.init();
        evolution.update();
        evolution.update();
        evolution.update();
        assertTrue("Evolution current Epoch age is 3", evolution.getCurrentEpochAge() == 0);
        assertTrue("Evolution current Epoch id is 2", evolution.getCurrentEpoch().getId() == 2);
    }

    @After
    public void tearDown() throws Exception {
        dataService.clearEvolution(evolution.getId());
    }
}
