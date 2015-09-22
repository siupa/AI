package aitests;

import com.aisim.aiapp.evolution.Evolution;
import com.aisim.aiapp.game.Actor;
import com.aisim.dal.EpochDataServiceImpl;
import com.aisim.dal.contracts.EpochDataService;
import com.aisim.dal.sqllite.EpochProbesSqlLiteDao;
import configuration.TestDefaultEvolutionConfiguration;
import org.junit.After;
import org.junit.Test;

import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;


/**
 * ai
 * Created by Krzysztof Slupski on 3/3/2015.
 */
public class EvolutionTests {

	Evolution evolution;
	EpochDataService dataService;

	public EvolutionTests() {
		dataService = new EpochDataServiceImpl(new EpochProbesSqlLiteDao("../database"));
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

	@Test
	public void actorsCreationAndLearningTest() throws Exception {
		List<Actor> actors = evolution.init();
		assertNotNull(actors);
		assertEquals("Number of actors should be equal the number of genomes from the population", evolution.getCurrentEpoch().getPopulation().getGenomes().size(), actors.size());
		for (Actor actor : actors) {
			actor.getBrain().learn(new Random().nextFloat());
			assertNotEquals("Fitness has been updated", 0, actor.getBrain().getFitness());
			assertEquals("Actors fitness is equal to the underlying genome fitness",
				evolution.getCurrentEpoch().getPopulation().getGenomes().get((int) actor.getId()).getFitness(),
				actor.getBrain().getFitness());
		}
	}

	@Test
	public void actorsDecidingTest() throws Exception {
		List<Actor> actors = evolution.init();
		for (Actor actor : actors) {
			Float output = actor.getBrain().decide(.2f, .1f, .8f);
			assertNotEquals("Actors decision is not zero", 0, output);
		}
	}

	@After
	public void tearDown() throws Exception {
		dataService.clearEvolution(evolution.getId());
	}
}
