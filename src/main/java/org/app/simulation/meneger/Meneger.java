package org.app.simulation.meneger;


import javafx.fxml.FXML;
import javafx.scene.layout.Pane;


import org.app.simulation.agent.anthill.Anthill;
import org.app.simulation.agent.food.Food;
import org.app.simulation.agent.pheromone.Pheromone;
import org.app.simulation.map.Map;
import org.app.simulation.meneger.config.Config;

import java.util.UUID;
import java.util.Vector;

import org.app.simulation.agent.ant.Ant;

/**
 * Odpowiada za zarządzanie symulacją.
 * Posiada adnotację @FXML i metodę "initialize()",
 * która jest wywoływana podczas inicjalizacji kontrolera FXML.
 */
public class Meneger {

    @FXML
    Pane world;
    private final Vector<Anthill> anthills;
    private int Tick = 0;
    private final Config settings;
    private final Map map;
    private final int CurrentTick = 0;

    /**
     * Konstruktor klasy Menager, inicjalizuje menedżera symulacji z określonymi ustawieniami i panelem.
     */
    public Meneger(Config settings, Pane world) {
        this.settings = settings;
        this.world = world;
        this.map = new Map(settings.getMapSizeX(),
                settings.getMapSizeY(),
                settings,
                world);

        this.anthills = new Vector<>();
    }

    /**
     * Dodaje losowe jedzenie na mapę.
     */
    public void AddRandomFood() {
        map.AddFood(new Food(settings));
        map.getFoods().get(map.getFoods().size() - 1).setRandomPosition();
    }

    /**
     * Inicjalizuje menedżera symulacji (metoda wywoływana podczas inicjalizacji kontrolera FXML).
     */
    @FXML
    public void initialize() {
        System.out.println("Menager init");
    }

    /**
     * Dodaje mrowisko na określonych współrzędnych.
     */
    public void addAnthill(double posx, double posy) {
        anthills.add(new Anthill(settings));
        anthills.get(anthills.size() - 1).setPosition(posx, posy);
    }

    /**
     * Dodaje mrowisko na losowych współrzędnych.
     */
    public void addAnthill() {
        anthills.add(new Anthill(settings));
        anthills.get(anthills.size() - 1).setRandomPosition();
    }

    /**
     * Zwraca wektor identyfikatorów wszystkich mrowisk.
     */
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

    /**
     * Dodaje określoną liczbę mrówek do mrowiska o określonym identyfikatorze.
     */
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

    /**
     * Wykonuje pojedynczy krok symulacji,
     * aktualizując wszystkie mrowiska i mapę.
     */
    public void PrzeprowadzTickSymulacji() {
        for (Anthill anthill : anthills) {
            anthill.update();
        }
        Tick++;
        map.Tick();
        if (settings.isPheromonesEvaporate()) {
            map.removePheromonesolderthan(settings.getPheromoneEvaporationTime());
        }
    }

    /**
     * Ustawia losowe pozycje dla wszystkich mrówek na mapie.
     */
    public void RandomAntPosition() {
        for (Anthill anthill : anthills) {
            for (Ant ant : anthill.getAnts()) {
                ant.setRandomPosition();
            }
        }
    }

    /**
     * Aktualizuje ustawienia wyświetlania dla wszystkich elementów
     * (mrowiska,
     * mrówek,
     * feromonów) na mapie.
     */
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
    }

    public int getTick() {
        return Tick;
    }

    /**
     * Usuwa wszystkie elementy z symulacji (mrowiska,
     * mrówki,
     * feromony,
     * jedzenie).
     */
    public void killeveryone() {
        for (Anthill anthill : anthills) {
            anthill.removeEverythingFromWorld();
        }
        anthills.clear();
        map.clearPhermonoes();
        map.clearFood();
    }

    /**
     * Zwraca liczbę wszystkich mrówek na mapie.
     */
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