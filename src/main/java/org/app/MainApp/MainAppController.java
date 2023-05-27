package org.app.MainApp;

import javafx.animation.AnimationTimer;
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

    Boolean isRunning = false;
    private Movement clock;

    @FXML
    public void initialize() {
        clock = new Movement();

        world.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));
        settings = new Config();
        sim = new Menager(settings, world);
        sim.addAnthill(settings.getMapSizeX() / 2, settings.getMapSizeY() / 2);
        sim.addAnts(10, sim.getAnthillIDs().get(0));
        sim.PrzeprowadzTickSymulacji();
    }

    @FXML
    public void step() {
        sim.PrzeprowadzTickSymulacji();
    }

    @FXML
    public void start() {
        clock.start();
    }

    @FXML
    public void stop() {
        clock.stop();
    }

    @FXML
    public void reset() {
        sim.RandomAntPosition();
    }

    private class Movement extends AnimationTimer {
        private long FRAMES_PER_SEC = 50L;
        private long INTERVAL = 10000000L / FRAMES_PER_SEC;
        private long last = 0;

        @Override
        public void handle(long now) {
            if (now - last > INTERVAL) {
                step();
                System.out.println("Tick : " + sim.getTick());
                last = now;
            }
        }

    }

}
