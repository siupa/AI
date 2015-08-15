package com.aisim.ai.ga;

import java.util.List;

/**
 * Created by Krzysztof on 3/5/2015.
 */
public class Genome {
    int id;
    List<Float> dna;

    public Genome()
    {

    }

    public int getId() {
        return id;
    }

    public List<Float> getDna() {
        return dna;
    }
}
