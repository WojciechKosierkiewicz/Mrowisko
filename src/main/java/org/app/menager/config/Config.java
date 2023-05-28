package org.app.menager.config;


import javafx.scene.layout.Pane;

import java.nio.file.Path;

public class Config {

    int MapSizeX = 1000;
    int MapSizeY = 900;

    int senseRange = 10;
    double AntTurnAngle = 0.001;

    int pheromoneCircleRadius = 2;
    int AntCircleRadius = 5;

    int AntLifetime = 1090;
    int AnthillAntLimit = 100;
    int AntStepLen = 1;

    double antDecisionFactor = 20;

    double antRange = 10;

    public double getAntTurnAngle() {
        return AntTurnAngle;
    }

    public int getPheromoneCircleRadius() {
        return pheromoneCircleRadius;
    }


    public int getSenseRange() {
        return senseRange;
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

    public double getAntDecisionFactor() {
        return antDecisionFactor;
    }

    public void setAntDecisionFactor(double antDecisionFactor) {
        this.antDecisionFactor = antDecisionFactor;
    }

    private int currentTick = 0;

    public double getAntRange() {
        return antRange;
    }

    public void setAntRange(double antRange) {
        this.antRange = antRange;
    }

    public int setCurrentTick() {
        return this.currentTick + 1;
    }

    public int getCurrentTick() {
        return currentTick;
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