import aitests.EvolutionTests;
import aitests.PerceptronTest;
import aitests.PopulationTests;
import dao.EpochDataServiceImplTest;
import dao.EpochProbesDaoTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * ai
 * Created by Krzysztof Slupski on 9/13/2015.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        EpochDataServiceImplTest.class,
        EpochProbesDaoTests.class,
        EvolutionTests.class,
        PerceptronTest.class,
        PopulationTests.class
})
public class AllTestsSuite {
}
