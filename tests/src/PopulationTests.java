import com.aisim.ai.ann.DefaultPerceptronConfiguration;
import com.aisim.ai.ann.DefaultPerceptronProvider;
import com.aisim.ai.ann.PerceptronConfiguration;
import com.aisim.ai.ga.Population;
import com.aisim.ai.ga.PopulationConfiguration;
import org.junit.Test;


/**
 * Created by Krzysztof on 3/3/2015.
 */
public class PopulationTests {

    @Test
    public void populationTest() {
        Population population = new Population(1, new PopulationConfiguration() {
            @Override
            public int getChromosomsCount() {
                return 10;
            }

            @Override
            public PerceptronConfiguration getPerceptronConfiguration() {
                return new DefaultPerceptronConfiguration();
            }
        }, new DefaultPerceptronProvider());

        System.out.print(population.toString());
    }
}
