package aitests;

import com.aisim.ai.ann.Perceptron;
import com.aisim.ai.ann.PerceptronProvider;
import com.aisim.ai.ga.Genome;
import configuration.TestDefaultPerceptronConfiguration;
import org.junit.Test;
import static org.junit.Assert.*;

public class GenomeTest {

	private Genome createGenome()
	{
		return new Genome(1, new PerceptronProvider() {
			@Override
			public Perceptron getPerceptron() {
				return new Perceptron(new TestDefaultPerceptronConfiguration());
			}
		});
	}
	@Test
	public void testHashCode() throws Exception {
		Genome g1 = createGenome();
		Genome g2 = createGenome();
		assertFalse(g1.hashCode() == g2.hashCode());
	}

	@Test
	public void testEquals() throws Exception {
		Genome g1 = createGenome();
		Genome g2 = createGenome();
		assertFalse(g1.equals(g2));
	}
}