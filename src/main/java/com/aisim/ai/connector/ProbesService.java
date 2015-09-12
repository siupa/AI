package com.aisim.ai.connector;

import java.util.List;

/**
 * ai
 * Created by Krzysztof Slupski on 9/12/2015.
 */
public interface ProbesService {
	void save(int evolutionId, int epochId, int genomeId, Float fitness, List<Float> weights) throws Exception;
}
