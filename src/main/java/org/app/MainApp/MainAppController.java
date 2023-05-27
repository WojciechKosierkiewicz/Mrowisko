package org.app.MainApp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import org.app.menager.Menager;
import javafx.scene.paint.Color;
import org.app.menager.config.Config;


public class MainAppController {
    @FXML
    Pane world;

    Config settings;
    Menager sim;

    @FXML
    public void initialize() {

        world.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));
        settings = new Config();
        sim = new Menager(settings, world);
        sim.addAnthill(settings.getMapSizeX() / 2, settings.getMapSizeY() / 2);
        sim.addAnts(10, sim.getAnthillIDs().get(0));
        sim.PrzeprowadzTickSymulacji();
    }
    @FXML
    public void reset() {
        sim.PrzeprowadzTickSymulacji();
    }
}
