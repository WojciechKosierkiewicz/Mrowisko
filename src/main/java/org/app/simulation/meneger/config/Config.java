package org.app.simulation.meneger.config;

/**
 * Odpowiada za przechowywanie ustawień symulacji w ramach projektu.
 * Zawiera zmienne dotyczące różnych aspektów symulacji,
 * takich jak mapa, mrówki,
 * feromony,
 * ustawienia wyświetlania itp.
 */

import javafx.scene.layout.Pane;
import org.app.simulation.map.Map;

public class Config {

    //zmienne "globalne" dla całego projektu <- Wszyscy agenci ich używają
    Pane world;
    Map map;
    int CurrentTick = 0;


    //ustawienia ogólne symulacji
    int OutsideMapTreshold = 10;

    //zmienne mapy
    int MapSizeX = 400;
    int MapSizeY = 400;
    int MapSectorSize = 10;


    //zmienne mrówek
    //możliwości ruchu mrowki
    double AntTurnAngleChange = 0.01;
    double AntTurnAngleMax = 0.01;
    double AntConsumption = 0.5;
    double AntStepLength = 0.5;

    //decyzyjność mrówki
    double AntProbabilityOfTakingPheromonesIntoAccount = 0.005;

    //zostawianie znakow przez mrowke
    int AntLeavePheromoneInterval = 10;

    //zasieg widzenie mrówki
    int AntViewRange = 10;
    double AntPheromoneSenseRange = 20;
    double AntFov = 1.2 * Math.PI;

    //prawa życia mrowki
    double AntLifetime = 5000;
    int AntFoodCapacity = 100;
    double AntMaximumHungerLevel = 100;
    boolean isAntGetHungry = true;
    boolean AntDiesOfAge = true;
    boolean isAntsVisible = true;


    //ustawienia anthills

    int AnthillAntsLimit = 100;
    double AnthillChanceOfAntSpawning = 0.009;


    //ustawienia pheromonów
    boolean isPheromonesEvaporate = true;
    int PheromoneEvaporationTime = 8000;
    int MaxPheromoneToHomeUsage = 1;
    int MaxPheromoneToFoodUsage = 5;

    boolean isPheromoneVisible = true;
    boolean isPheromoneToHomeVisible = false;
    boolean isPheromoneToFoodVisible = true;
    boolean isPheromoneToHomeUsedUp = true;
    boolean isPheromoneToFoodUsedUp = true;


    //ustawienia wyswietlania
    double DisplayAntSize = 3;
    double DisplayAnthillSize = 5;
    double DisplayPheromoneSize = 1;
    double DisplayFoodSize = 5;


// gettery i settery dla calego projektu


    public Config(Pane world) {
        this.world = world;
    }

    public Pane getWorld() {
        return world;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public int getCurrentTick() {
        return CurrentTick;
    }


//gettery i settery ogolne symulacji


    public int getOutsideMapTreshold() {
        return OutsideMapTreshold;
    }


//gettery i settery mapy


    public int getMapSizeX() {
        return MapSizeX;
    }


    public int getMapSizeY() {
        return MapSizeY;
    }


    public int getMapSectorSize() {
        return MapSectorSize;
    }


//gettery i settery mrówek

    //mozliwości ruchu mrówki


    public double getAntPheromoneSenseRange() {
        return AntPheromoneSenseRange;
    }

    public double getAntTurnAngleMax() {
        return AntTurnAngleMax;
    }


    public double getAntTurnAngleChange() {
        return AntTurnAngleChange;
    }


    public double getAntConsumption() {
        return AntConsumption;
    }


    public double getAntStepLengthh() {
        return AntStepLength;
    }


    //decyzyjność mrówki


    public double getAntProbabilityOfTakingPheromonesIntoAccount() {
        return AntProbabilityOfTakingPheromonesIntoAccount;
    }


    //zostawianie znaków przez mrówke


    public int getAntLeavePheromoneInterval() {
        return AntLeavePheromoneInterval;
    }


    //zasieg widzenia mrówki


    public int getAntViewRange() {
        return AntViewRange;
    }


    public double getAntFov() {
        return AntFov;
    }


    //prawa zycia mrówki


    public double getAntLifetime() {
        return AntLifetime;
    }


    public double getAntMaximumHungerLevel() {
        return AntMaximumHungerLevel;
    }


    public boolean isAntGetHungry() {
        return isAntGetHungry;
    }


//gettery i settery anthills


    public int getAnthillAntsLimit() {
        return AnthillAntsLimit;
    }

    public double getAnthillChanceOfAntSpawning() {
        return AnthillChanceOfAntSpawning;
    }


//gettery i settery phermomonów


    public boolean isPheromonesEvaporate() {
        return isPheromonesEvaporate;
    }


    public int getPheromoneEvaporationTime() {
        return PheromoneEvaporationTime;
    }


    public boolean isPheromoneVisible() {
        return isPheromoneVisible;
    }


    public boolean isPheromoneToFoodVisible() {
        return isPheromoneToFoodVisible;
    }


    public boolean isPheromoneToHomeVisible() {
        return isPheromoneToHomeVisible;
    }

    public int getMaxPheromoneToFoodUsage() {
        return MaxPheromoneToFoodUsage;
    }

    public void setMaxPheromoneToFoodUsage(int maxPheromoneToFoodUsage) {
        MaxPheromoneToFoodUsage = maxPheromoneToFoodUsage;
    }

    public int getMaxPheromoneToHomeUsage() {
        return MaxPheromoneToHomeUsage;
    }

    public void setMaxPheromoneToHomeUsage(int maxPheromoneToHomeUsage) {
        MaxPheromoneToHomeUsage = maxPheromoneToHomeUsage;
    }

    public boolean isPheromoneToFoodUsedUp() {
        return isPheromoneToFoodUsedUp;
    }

    public boolean isPheromoneToHomeUsedUp() {
        return isPheromoneToHomeUsedUp;
    }

    //gettery i settery wyswietlania


    public double getDisplayAntSize() {
        return DisplayAntSize;
    }


    public double getDisplayAnthillSize() {
        return DisplayAnthillSize;
    }


    public double getDisplayPheromoneSize() {
        return DisplayPheromoneSize;
    }


    public double getDisplayFoodSize() {
        return DisplayFoodSize;
    }
}