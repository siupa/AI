package com.aisim.dal;

import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

import java.util.List;

/**
 * ai
 * Created by Krzysztof Slupski on 8/18/2015.
 */
@Entity
public class Probe {
	@Id
	private int id;
	private List<Float> weights;
	public int getId() {
		return id;
	}

	public List<Float> getWeights() {
		return weights;
	}

	public void setWeights(List<Float> weights) {
		this.weights = weights;
	}

	public void setId(int id) {
		this.id = id;
	}
}
