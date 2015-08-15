import com.aisim.ai.ann.DefaultPerceptronConfiguration;
import com.aisim.ai.ann.Perceptron;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class PerceptronTest {

    @Test
    public void testCalculate() throws Exception {
        Perceptron perceptron = new Perceptron(new DefaultPerceptronConfiguration());
        runTests(perceptron);
    }

    void runTests(Perceptron perceptron) {
        LinkedList<Float> out;
        int inputSize = perceptron.getConfiguration().getInputsCount();
        Float[] in = new Float[inputSize];
        System.out.print(perceptron.toString());
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < inputSize; j++) {
                in[j] = (float) Math.random();
            }

            print("in", java.util.Arrays.asList(in));
            out = perceptron.calculate(in);
            print("out", out);
        }
    }

    private void print(String name, List<Float> result) {
        StringBuilder builder = new StringBuilder();
        builder.append(name);
        for (Float n : result) {
            builder.append("[" + n.toString() + "]");
        }
        builder.append("\n");
        System.out.print(builder.toString());
    }
}