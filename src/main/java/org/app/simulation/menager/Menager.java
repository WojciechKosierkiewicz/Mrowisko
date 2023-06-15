package org.app.simulation.menager;


import javafx.fxml.FXML;
import javafx.scene.layout.Pane;


import org.app.simulation.agent.anthill.Anthill;
import org.app.simulation.agent.food.Food;
import org.app.simulation.agent.pheromone.Pheromone;
import org.app.simulation.map.Map;
import org.app.simulation.menager.config.Config;

import java.util.UUID;
import java.util.Vector;

import org.app.simulation.agent.ant.Ant;

public class Menager {

    @FXML
    Pane world;
    private final Vector<Anthill> anthills;
    private int Tick = 0;
    private final Config settings;
    private final Map map;
    private final int CurrentTick = 0;

    public Menager(Config settings, Pane world) {
        this.settings = settings;
        this.world = world;
        this.map = new Map(settings.getMapSizeX(),
                settings.getMapSizeY(),
                settings,
                world);

        this.anthills = new Vector<>();
    }

    public void AddRandomFood() {
        map.AddFood(new Food(settings));
        map.getFoods().get(map.getFoods().size() - 1).setRandomPosition();
    }

    @FXML
    public void initialize() {
        System.out.println("Menager init");
    }

    public void addAnthill(double posx, double posy) {
        anthills.add(new Anthill(settings));
        anthills.get(anthills.size() - 1).setPosition(posx, posy);
    }

    public void addAnthill() {
        anthills.add(new Anthill(settings));
        anthills.get(anthills.size() - 1).setRandomPosition();
    }

    public Vector<UUID> getAnthillIDs() {
        Vector<UUID> anthillIDs = new Vector<>();
        for (Anthill anthill : anthills) {
            anthillIDs.add(anthill.getId());
        }
        return anthillIDs;
    }

    public Map getMap() {
        return map;
    }

    public void addAnts(int amount, UUID anthillID) {
        for (Anthill anthill : anthills) {
            if (anthill.getId() == anthillID) {
                for (int i = 0; i < amount; i++) {
                    anthill.addAnt();
                }
                return;
            }
        }
    }

    public void PrzeprowadzTickSymulacji() {
        for (Anthill anthill : anthills) {
            anthill.update();
        }
        Tick++;
        map.Tick();
        if (settings.isPheromonesEvaporate()) {
            map.removePheromonesolderthan(settings.getPheromoneEvaporationTime());
        }
        map.RemoveOverlyUsedPheromones();
    }

    public void RandomAntPosition() {
        for (Anthill anthill : anthills) {
            for (Ant ant : anthill.getAnts()) {
                ant.setRandomPosition();
            }
        }
    }

    public void updatealldisplayConfigs() {
        for (Anthill anthill : anthills) {
            anthill.updateJavaFxShapeSettings();
            for (Ant ant : anthill.getAnts()) {
                ant.updateJavaFxShapeSettings();
            }
        }
        for (Pheromone pher : map.getPheromones()) {
            pher.updateJavaFxShapeSettings();
        }
        if (settings.isPheromoneVisible()) {
        }
    }

    public int getTick() {
        return Tick;
    }


    public void killeveryone() {
        for (Anthill anthill : anthills) {
            anthill.removeEverythingFromWorld();
        }
        anthills.clear();
        map.clearPhermonoes();
        map.clearFood();
    }

    public int getAmountofants() {
        int amount = 0;
        for (Anthill anthill : anthills) {
            amount += anthill.getAnts().size();
        }
        return amount;
    }

    public int getAmountofphermoones() {
        return map.getamountofpheromones();
    }

    void ZapiszWyniki() {
        // TODO: 15.05.2023   
    }
}