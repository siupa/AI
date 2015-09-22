package com.aisim.aiapp;

import com.aisim.aiapp.evolution.DefaultEvolutionConfiguration;
import com.aisim.aiapp.evolution.Evolution;
import com.aisim.aiapp.game.Actor;
import com.aisim.dal.EpochDataServiceImpl;
import com.aisim.dal.sqllite.EpochProbesSqlLiteDao;
import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

import java.util.List;
import java.util.Random;

public class Main extends SimpleApplication {

	private List<Actor> actors;
	private Hud hud;
	private Evolution evolution;

	public Main() {
		hud = new Hud(this);
		evolution = new Evolution(
			new DefaultEvolutionConfiguration(), new EpochDataServiceImpl(new EpochProbesSqlLiteDao("../database")
		));
	}

	public static void main(String[] args) {
		Main app = new Main();
		app.start();
	}

	@Override
	public void simpleInitApp() {
		actors = evolution.init();

		Box b = new Box(1, 1, 1);
		Geometry geom = new Geometry("Box", b);
		Material mat = new Material(assetManager, "Common/MatDefs/Misc/Unshaded.j3md");
		mat.setColor("Color", ColorRGBA.Blue);
		geom.setMaterial(mat);
		rootNode.attachChild(geom);

		hud.init(guiFont);
	}

	@Override
	public void simpleUpdate(float tpf) {
		for (Actor actor : actors) {
			actor.getBrain().learn(new Random().nextFloat());
		}
		if (evolution.update()) {
			hud.update(evolution);
		}
	}
}
