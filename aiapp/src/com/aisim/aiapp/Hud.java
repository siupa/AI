package com.aisim.aiapp;

import com.aisim.aiapp.evolution.Evolution;
import com.jme3.app.SimpleApplication;
import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;

/**
 * ai
 * Created by Krzysztof on 8/30/2015.
 */
public class Hud {

    SimpleApplication app;
    BitmapText text;

    public Hud(SimpleApplication app) {
        this.app = app;
    }

    public void init(BitmapFont font)
    {
        text = new BitmapText(font, false);
        text.setSize(12);
        text.setText("Hud");
        text.setLocalTranslation(0, text.getLineHeight() + 300 , 0);
        app.getGuiNode().attachChild(text);
    }

    public void update(Evolution evolution)
    {
       text.setText(String.format("Current Epoch: %d (age: %d)", evolution.getCurrentEpoch().getId(), evolution.getCurrentEpochAge()));
    }
}
