package aitests;

import com.aisim.ai.ann.Perceptron;
import com.aisim.ai.ann.PerceptronProvider;
import com.aisim.ai.ga.Population;
import com.aisim.ai.ga.PopulationConfiguration;
import configuration.TestDefaultPopulationConfiguration;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * ai
 * Created by Krzysztof Slupski on 3/3/2015.
 */
public class PopulationTests {

    Population population;
    public PopulationTests() {
        final PopulationConfiguration configuration = new TestDefaultPopulationConfiguration();
        population = new Population(configuration, new PerceptronProvider() {
            @Override
            public Perceptron getPerceptron() {
                return new Perceptron(configuration.getPerceptronConfiguration());
            }
        });
    }
    @Test
    public void populationTest() {
        Population newPopulation = population.reproduce();
        assertEquals("New population after reproducing process should be of the same size", newPopulation.getGenomes().size(), population.getGenomes().size());
        assertTrue("New population is different from the original one", population.getGenomes().retainAll(newPopulation.getGenomes()));
    }
}
