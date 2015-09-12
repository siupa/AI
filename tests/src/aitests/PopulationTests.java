package aitests;

import com.aisim.ai.ann.DefaultPerceptronProvider;
import com.aisim.ai.ga.DefaultPopulationConfiguration;
import com.aisim.ai.ga.Population;
import org.junit.Test;


/**
 * ai
 * Created by Krzysztof Slupski on 3/3/2015.
 */
public class PopulationTests {

    @Test
    public void populationTest() {
        Population population = new Population(new DefaultPopulationConfiguration(), new DefaultPerceptronProvider());
        System.out.print(population);
    }
}
