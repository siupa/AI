import com.aisim.ai.ann.DefaultPerceptronConfiguration;
import com.aisim.ai.ann.DefaultPerceptronProvider;
import com.aisim.ai.ann.PerceptronConfiguration;
import com.aisim.ai.ga.*;
import org.junit.Test;


/**
 * ai
 * Created by Krzysztof Slupski on 3/3/2015.
 */
public class EvolutionTests {

	@Test
	public void evolutionTest() {
		Evolution evolution = new Evolution(new EvolutionConfiguration() {
			@Override
			public int getEpochsCount() {
				return 1;
			}

			@Override
			public PopulationConfiguration getPopulationConfiguration() {
				return new PopulationConfiguration() {
					@Override
					public int getChromosomsCount() {
						return 10;
					}

					@Override
					public PerceptronConfiguration getPerceptronConfiguration() {
						return new DefaultPerceptronConfiguration();
					}
				};
			}
		});

		evolution.run();

	}
}
