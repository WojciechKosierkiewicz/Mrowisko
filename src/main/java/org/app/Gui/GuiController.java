package org.app.Gui;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import org.app.simulation.menager.Menager;
import javafx.scene.paint.Color;
import org.app.simulation.menager.config.Config;


public class GuiController {
    @FXML
    Pane world;

    Config settings;
    Menager sim;

    Boolean isRunning = false;
    private TimeKeeper clock;

    XYChart.Series populacjaxy = new XYChart.Series();
    XYChart.Series pheromonyxy = new XYChart.Series();

    @FXML
    Slider AntSizeSlide = new Slider(0, 10, 5);

    @FXML
    Slider AntLifeTimeSlide = new Slider(0, 10000, 1500);

    @FXML
    Slider AntRangeSlide = new Slider(0, 100, 10);

    @FXML
    Slider AntStepLengthSlide = new Slider(0, 10, 1);

    @FXML
    Slider AntTurnAngleMaxSlide = new Slider(0, 1, 0.01);

    @FXML
    Slider AnthillCircleRadiusSlide = new Slider(0, 10, 10);

    @FXML
    Slider PheromoneCircleRadiusSlide = new Slider(0, 10, 2);
    @FXML
    TextField ticktext;
    @FXML
    private AreaChart<?, ?> populacjaant;


    @FXML
    void UpdateSettings() {
        settings.setAntCircleRadius(AntSizeSlide.getValue());
        settings.setAntLifetime(AntLifeTimeSlide.getValue());
        settings.setAntRange(AntRangeSlide.getValue());
        settings.setAntStepLen(AntStepLengthSlide.getValue());
        settings.setAntTurnAngleMax(AntTurnAngleMaxSlide.getValue());
        settings.setAntHillCircleRadius(AnthillCircleRadiusSlide.getValue());
        settings.setPheromoneCircleRadius(PheromoneCircleRadiusSlide.getValue());

        sim.updatealldisplayConfigs();
    }

    @FXML
    public void initialize() {
        clock = new TimeKeeper();
        world.setBackground(new Background(new BackgroundFill(Color.BEIGE, null, null)));
        settings = new Config(world);
        sim = new Menager(settings, world);
        settings.setMap(sim.getMap());
        sim.addAnthill();
        sim.addAnts(10, sim.getAnthillIDs().get(0));
        sim.PrzeprowadzTickSymulacji();

        if (populacjaant.getData().size() == 0 && iloscpheromonow.getData().size() == 0) {
            populacjaant.getData().add(populacjaxy);
            iloscpheromonow.getData().add(pheromonyxy);
        }
    }

    @FXML
    private AreaChart<?, ?> iloscpheromonow;

    @FXML
    public void step() {
        sim.PrzeprowadzTickSymulacji();
        if (sim.getTick() % 100 == 0) {
            populacjaxy.getData().add(new XYChart.Data(Integer.toString(sim.getTick() - 100), sim.getAmountofants()));
            pheromonyxy.getData().add(new XYChart.Data(Integer.toString(sim.getTick() - 100), sim.getAmountofphermoones()));
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
        clock.stop();
        sim.killeveryone();
        clearGraphs();
        initialize();
    }

    public void clearGraphs() {
        populacjaxy.getData().clear();
        pheromonyxy.getData().clear();
    }


    private class TimeKeeper extends AnimationTimer {
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
