package org.app.simulation.menager.config;


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
    int MapSizeX = 600;
    int MapSizeY = 600;


    //zmienne mrówek
    int AntSenseRange = 10;
    double AntLifetime = 1500;
    double AntTurnAngleChange = 0.001;
    double AntTurnAngleMax = 0.01;
    double AntConsumption = 0.5;
    int AntFoodCapacity = 100;
    double AntHungerLimit = 100;
    double AntStepLen = 0.5;
    double antRange = 10;
    int AntPheromoneInterval = 20;
    double AntFov = 0.6 * Math.PI;
    boolean AntGetHungry = true;
    boolean AntDiesOfAge = true;

    boolean doAntsEvaporate = true;
    double AntsEvaporationTime = 1000;
    boolean isAntsVisible = true;
    double antFeedingRange = 1;


    //ustawienia anthills

    int AnthillAntLimit = 100;
    double AnthillAntSpawnChance = 0.1;


    //ustawienia pheromonów
    boolean doPheromonesEvaporate = true;
    int PheromoneEvaporationTime = 100000;
    boolean isPheromoneVisible = true;


    //ustawienia wyswietlania
    double AntCircleRadius = 3;
    double AntHillCircleRadius = 5;
    double pheromoneCircleRadius = 1;
    double FoodCircleRadius = 5;

    public double getFoodCircleRadius() {
        return FoodCircleRadius;
    }

    public double getAntFeedingRange() {
        return antFeedingRange;
    }

    public Config(Pane world) {
        this.world = world;
    }

    public int getPheromoneEvaporationTime() {
        return PheromoneEvaporationTime;
    }

    public int getAntPheromoneInterval() {
        return AntPheromoneInterval;
    }

    public double getAntHillCircleRadius() {
        return AntHillCircleRadius;
    }

    public int getOutsideMapTreshold() {
        return OutsideMapTreshold;
    }


    public int getCurrentTick() {
        return CurrentTick;
    }

    public Map getMap() {
        return map;
    }

    public double getPheromoneCircleRadius() {
        return pheromoneCircleRadius;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public boolean isPheromoneVisible() {
        return isPheromoneVisible;
    }

    public Pane getWorld() {
        return world;
    }

    public double getAnthillAntSpawnChance() {
        return AnthillAntSpawnChance;
    }

    public double getAntTurnAngleMax() {
        return AntTurnAngleMax;
    }

    public double getAntTurnAngleChange() {
        return AntTurnAngleChange;
    }

    public double getAntCircleRadius() {
        return AntCircleRadius;
    }


    public int getSenseRange() {
        return AntSenseRange;
    }

    public int getMapSizeX() {
        return MapSizeX;
    }

    public int getMapSizeY() {
        return MapSizeY;
    }

    public boolean isDoPheromonesEvaporate() {
        return doPheromonesEvaporate;
    }

    public void setDoPheromonesEvaporate(boolean doPheromonesEvaporate) {
        this.doPheromonesEvaporate = doPheromonesEvaporate;
    }

    public double getAntStepLen() {
        return AntStepLen;
    }

    public void setAntCircleRadius(double antCircleRadius) {
        AntCircleRadius = antCircleRadius;
    }

    public double getAntRange() {
        return antRange;
    }

    public void setAntRange(double antRange) {
        this.antRange = antRange;
    }

    public int getAnthillAntLimit() {
        return AnthillAntLimit;
    }

    public void setAnthillAntLimit(int anthillAntLimit) {
        AnthillAntLimit = anthillAntLimit;
    }

    public void setAntStepLen(double antStepLen) {
        AntStepLen = antStepLen;
    }

    public double getAntLifetime() {
        return AntLifetime;
    }

    public void setAntTurnAngleMax(double antTurnAngleMax) {
        AntTurnAngleMax = antTurnAngleMax;
    }

    public void setAntHillCircleRadius(double antHillCircleRadius) {
        AntHillCircleRadius = antHillCircleRadius;
    }

    public void setPheromoneCircleRadius(double pheromoneCircleRadius) {
        this.pheromoneCircleRadius = pheromoneCircleRadius;
    }

    public void setPheromoneVisible(boolean pheromoneVisible) {
        isPheromoneVisible = pheromoneVisible;
    }

    public void setAntLifetime(double antLifetime) {
        AntLifetime = antLifetime;
    }

    public double getAntHungerLimit() {
        return AntHungerLimit;
    }

    public void setAntHungerLimit(int antHungerLimit) {
        AntHungerLimit = antHungerLimit;
    }

    public double getAntConsumption() {
        return AntConsumption;
    }

    public void setAntConsumption(double antConsumption) {
        AntConsumption = antConsumption;
    }

    public int getAntFoodCapacity() {
        return AntFoodCapacity;
    }

    public void setAntFoodCapacity(int antFoodCapacity) {
        AntFoodCapacity = antFoodCapacity;
    }

    public boolean isAntGetHungry() {
        return AntGetHungry;
    }

    public boolean isAntDiesOfAge() {
        return AntDiesOfAge;
    }

    public void setPheromoneEvaporationTime(int pheromoneEvaporationTime) {
        PheromoneEvaporationTime = pheromoneEvaporationTime;
    }

    public void setAntGetHungry(boolean antGetHungry) {
        AntGetHungry = antGetHungry;
    }

    public void setAntDiesOfAge(boolean antDiesOfAge) {
        AntDiesOfAge = antDiesOfAge;
    }

    public void setDoAntsEvaporate(boolean doAntsEvaporate) {
        this.doAntsEvaporate = doAntsEvaporate;
    }

    public void setAntsVisible(boolean antsVisible) {
        isAntsVisible = antsVisible;
    }

    public void setAntTurnAngleChange(double antTurnAngleChange) {
        AntTurnAngleChange = antTurnAngleChange;
    }

    public void setAntPheromoneInterval(int antPheromoneInterval) {
        AntPheromoneInterval = antPheromoneInterval;
    }

    public void setAntsEvaporationTime(double antsEvaporationTime) {
        AntsEvaporationTime = antsEvaporationTime;
    }

    public double getAntFov() {
        return AntFov;
    }
}