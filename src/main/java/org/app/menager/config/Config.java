package org.app.menager.config;


import javafx.scene.layout.Pane;
import org.app.map.Map;

public class Config {

    //zmienne "globalne" dla całego projektu <- Wszyscy agenci ich używają
    Pane world;
    org.app.map.Map map;
    int CurrentTick = 0;


    //ustawienia ogólne sym
    int OutsideMapTreshold = 2;

    //zmienne mapy
    int MapSizeX = 1000;
    int MapSizeY = 900;


    //zmienne mrówek
    int AntSenseRange = 10;
    double AntLifetime = 1500;
    double AntTurnAngle = 0.001;
    double AntTurnAngleMax = 0.01;

    double AntStepLen = 1;

    double antRange = 10;

    //ustawienia anthills

    int AnthillAntLimit = 100;
    double AnthillAntSpawnChance = 0.1;


    //ustawienia wyswietlania
    double AntCircleRadius = 5;
    double AntHillCircleRadius = 10;
    double pheromoneCircleRadius = 2;


    public Config(Pane world) {
        this.world = world;
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

    public Pane getWorld() {
        return world;
    }

    public double getAnthillAntSpawnChance() {
        return AnthillAntSpawnChance;
    }

    public double getAntTurnAngleMax() {
        return AntTurnAngleMax;
    }

    public double getAntTurnAngle() {
        return AntTurnAngle;
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

    public void setAntLifetime(double antLifetime) {
        AntLifetime = antLifetime;
    }
}