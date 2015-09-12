package aitests;

import com.aisim.aiapp.evolution.DefaultEvolutionConfiguration;
import com.aisim.aiapp.evolution.Evolution;
import com.aisim.aiapp.evolution.simulator.DisplayImpl;
import org.junit.Test;


/**
 * ai
 * Created by Krzysztof Slupski on 3/3/2015.
 */
public class EvolutionTests {

    @Test
    public void evolutionTest() {
        Evolution evolution = new Evolution(new DefaultEvolutionConfiguration(), new DisplayImpl());
        evolution.init();
    }
}
