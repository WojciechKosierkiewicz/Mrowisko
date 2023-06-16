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
    int MapSizeX = 800;
    int MapSizeY = 800;
    int MapSectorSize = 10;


    //zmienne mrówek
    //możliwości ruchu mrowki
    double AntTurnAngleChange = 0.01;
    double AntTurnAngleMax = 0.1;
    double AntConsumption = 0.5;
    double AntStepLength = 0.5;

    //decyzyjność mrówki
    double AntProbabilityOfTakingPheromonesIntoAccount = 0.01;

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


    //temp do ułożenia


    public void setAntFoodCapacity(int antFoodCapacity) {
        AntFoodCapacity = antFoodCapacity;
    }

    public void setAntConsumption(double antConsumption) {
        AntConsumption = antConsumption;
    }

    public void setAntDiesOfAge(boolean antDiesOfAge) {
        AntDiesOfAge = antDiesOfAge;
    }

    public void setAntFov(double antFov) {
        AntFov = antFov;
    }

    public void setAntGetHungry(boolean antGetHungry) {
        isAntGetHungry = antGetHungry;
    }

    public void setAnthillAntsLimit(int anthillAntsLimit) {
        AnthillAntsLimit = anthillAntsLimit;
    }

    public void setAnthillChanceOfAntSpawning(double anthillChanceOfAntSpawning) {
        AnthillChanceOfAntSpawning = anthillChanceOfAntSpawning;
    }

    public void setAntLeavePheromoneInterval(int antLeavePheromoneInterval) {
        AntLeavePheromoneInterval = antLeavePheromoneInterval;
    }

    public void setAntLifetime(double antLifetime) {
        AntLifetime = antLifetime;
    }

    public void setAntMaximumHungerLevel(double antMaximumHungerLevel) {
        AntMaximumHungerLevel = antMaximumHungerLevel;
    }

    public void setAntPheromoneSenseRange(double antPheromoneSenseRange) {
        AntPheromoneSenseRange = antPheromoneSenseRange;
    }

    public void setAntProbabilityOfTakingPheromonesIntoAccount(double antProbabilityOfTakingPheromonesIntoAccount) {
        AntProbabilityOfTakingPheromonesIntoAccount = antProbabilityOfTakingPheromonesIntoAccount;
    }

    public void setAntStepLength(double antStepLength) {
        AntStepLength = antStepLength;
    }

    public void setAntsVisible(boolean antsVisible) {
        isAntsVisible = antsVisible;
    }

    public void setAntTurnAngleChange(double antTurnAngleChange) {
        AntTurnAngleChange = antTurnAngleChange;
    }

    public void setAntTurnAngleMax(double antTurnAngleMax) {
        AntTurnAngleMax = antTurnAngleMax;
    }

    public void setAntViewRange(int antViewRange) {
        AntViewRange = antViewRange;
    }

    public void setCurrentTick(int currentTick) {
        CurrentTick = currentTick;
    }

    public void setDisplayAnthillSize(double displayAnthillSize) {
        DisplayAnthillSize = displayAnthillSize;
    }

    public void setDisplayAntSize(double displayAntSize) {
        DisplayAntSize = displayAntSize;
    }

    public void setDisplayFoodSize(double displayFoodSize) {
        DisplayFoodSize = displayFoodSize;
    }

    public void setDisplayPheromoneSize(double displayPheromoneSize) {
        DisplayPheromoneSize = displayPheromoneSize;
    }

    public void setMapSectorSize(int mapSectorSize) {
        MapSectorSize = mapSectorSize;
    }

    public void setMapSizeX(int mapSizeX) {
        MapSizeX = mapSizeX;
    }

    public void setMapSizeY(int mapSizeY) {
        MapSizeY = mapSizeY;
    }

    public void setOutsideMapTreshold(int outsideMapTreshold) {
        OutsideMapTreshold = outsideMapTreshold;
    }

    public void setPheromoneEvaporationTime(int pheromoneEvaporationTime) {
        PheromoneEvaporationTime = pheromoneEvaporationTime;
    }

    public void setPheromonesEvaporate(boolean pheromonesEvaporate) {
        isPheromonesEvaporate = pheromonesEvaporate;
    }

    public void setPheromoneToFoodUsedUp(boolean pheromoneToFoodUsedUp) {
        isPheromoneToFoodUsedUp = pheromoneToFoodUsedUp;
    }

    public void setPheromoneToFoodVisible(boolean pheromoneToFoodVisible) {
        isPheromoneToFoodVisible = pheromoneToFoodVisible;
    }

    public void setPheromoneToHomeUsedUp(boolean pheromoneToHomeUsedUp) {
        isPheromoneToHomeUsedUp = pheromoneToHomeUsedUp;
    }

    public void setPheromoneToHomeVisible(boolean pheromoneToHomeVisible) {
        isPheromoneToHomeVisible = pheromoneToHomeVisible;
    }

    public void setPheromoneVisible(boolean pheromoneVisible) {
        isPheromoneVisible = pheromoneVisible;
    }

    public void setWorld(Pane world) {
        this.world = world;
    }
}