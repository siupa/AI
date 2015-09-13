package aitests;

import com.aisim.aiapp.evolution.DefaultEvolutionConfiguration;
import com.aisim.aiapp.evolution.Evolution;
import configuration.TestDefaultEvolutionConfiguration;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 * ai
 * Created by Krzysztof Slupski on 3/3/2015.
 */
public class EvolutionTests {

    Evolution evolution;

    public EvolutionTests() {
        evolution = new Evolution(new TestDefaultEvolutionConfiguration());
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
        assertTrue(evolution.getCurrentEpochAge() == 2);
        assertTrue(evolution.getCurrentEpoch().getId() == 1);
    }
}
