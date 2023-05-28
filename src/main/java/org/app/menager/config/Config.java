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
    int AntLifetime = 1500;
    double AntTurnAngle = 0.001;
    double AntTurnAngleMax = 0.01;

    int AntStepLen = 1;

    double antRange = 10;

    //ustawienia anthills

    int AnthillAntLimit = 100;
    double AnthillAntSpawnChance = 0.1;


    //ustawienia wyswietlania
    int AntCircleRadius = 5;
    int AntHillCircleRadius = 10;
    int pheromoneCircleRadius = 2;


    public Config(Pane world) {
        this.world = world;
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

    public int getAntHillCircleRadius() {
        return AntHillCircleRadius;
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

    public int getPheromoneCircleRadius() {
        return pheromoneCircleRadius;
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

    public int getAntStepLen() {
        return AntStepLen;
    }

    public int getAntCircleRadius() {
        return AntCircleRadius;
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

    public int getAntLifetime() {
        return AntLifetime;
    }

    public void setAntLifetime(int antLifetime) {
        AntLifetime = antLifetime;
    }
}