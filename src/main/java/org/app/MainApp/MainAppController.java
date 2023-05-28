package org.app.MainApp;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import org.app.map.Map;
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

    XYChart.Series populacjaxy = new XYChart.Series();
    XYChart.Series pheromonyxy = new XYChart.Series();
    @FXML
    TextField ticktext;
    @FXML
    private AreaChart<?, ?> populacjaant;

    @FXML
    public void initialize() {
        clock = new Movement();
        world.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));
        settings = new Config(world);
        sim = new Menager(settings, world);
        settings.setMap(sim.getMap());
        sim.addAnthill();
        sim.addAnts(10, sim.getAnthillIDs().get(0));
        sim.PrzeprowadzTickSymulacji();
    }
    @FXML
    private AreaChart<?, ?> iloscpheromonow;

    @FXML
    public void step() {
        sim.PrzeprowadzTickSymulacji();
        if (sim.getTick() % 100 == 0) {
            populacjaxy.getData().add(new XYChart.Data(Integer.toString(sim.getTick()), sim.getAmountofants()));
            pheromonyxy.getData().add(new XYChart.Data(Integer.toString(sim.getTick()), sim.getAmountofphermoones()));
            populacjaant.getData().add(populacjaxy);
            iloscpheromonow.getData().add(pheromonyxy);
        }
        ticktext.setText(Integer.toString(sim.getTick()));
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
        sim.killeveryone();
        initialize();
    }

    private class Movement extends AnimationTimer {
        private long FRAMES_PER_SEC = 10L;
        private long INTERVAL = 1000000L / FRAMES_PER_SEC;
        private long last = 0;

        @Override
        public void handle(long now) {
            if (now - last > INTERVAL) {
                step();
                last = now;
            }
        }

    }

}
