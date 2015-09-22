package com.aisim.aiapp.game;

/**
 * ai
 * Created by Krzysztof Slupski on 9/22/2015.
 */
public class Actor {

	private final long id;

	private final Brain brain;

	public long getId() {
		return id;
	}

	public Brain getBrain() {
		return brain;
	}

	public Actor(long id, Brain brain) {
		this.id = id;
		this.brain = brain;
	}

	public void move(){}
	public void attack(){}
	public void pickBonus(){}
	public void pickMed(){}



}
