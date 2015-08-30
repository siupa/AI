package aitests;

import com.aisim.ai.ann.DefaultPerceptronConfiguration;
import com.aisim.ai.ann.PerceptronConfiguration;
import com.aisim.ai.ga.Epoch;
import com.aisim.ai.ga.PopulationConfiguration;
import com.aisim.aiapp.evolution.Evolution;
import com.aisim.aiapp.evolution.EvolutionConfiguration;
import com.aisim.aiapp.evolution.simulator.Display;
import com.aisim.aiapp.evolution.simulator.LiveSimulator;
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
            public long getEpochLengthInTimeUnits() {
                return 10;
            }

            @Override
            public PopulationConfiguration getPopulationConfiguration() {
                return new PopulationConfiguration() {
                    @Override
                    public int getChromosomsCount() {
                        return 10;
                    }

                    @Override
                    public Float getElitePercentage() {
                        return .1f;
                    }

                    @Override
                    public PerceptronConfiguration getPerceptronConfiguration() {
                        return new DefaultPerceptronConfiguration();
                    }
                };
            }
        }, new Display() {
            @Override
            public void out(Object content) {
                System.out.print(content);
            }
        }, new LiveSimulator() {
            @Override
            public void simulate(Epoch population) {

            }
        });
        evolution.init();
    }
}
