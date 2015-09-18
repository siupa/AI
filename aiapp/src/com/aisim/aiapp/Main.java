package com.aisim.aiapp;

import com.aisim.aiapp.evolution.DefaultEvolutionConfiguration;
import com.aisim.aiapp.evolution.Evolution;
import com.aisim.dal.EpochDataServiceImpl;
import com.aisim.dal.EpochProbesSqlLiteDao;
import com.jme3.app.SimpleApplication;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

public class Main extends SimpleApplication {

    private Hud hud;
    private Evolution evolution;

    public Main() {
        hud = new Hud(this);
        evolution = new Evolution(
                new DefaultEvolutionConfiguration(), new EpochDataServiceImpl(new EpochProbesSqlLiteDao("../database"),
                new DefaultEvolutionConfiguration().getPopulationConfiguration().getPerceptronConfiguration()));
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    @Override
    public void simpleInitApp() {
        evolution.init();

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
        evolution.update();
        hud.update(evolution);
    }
}
