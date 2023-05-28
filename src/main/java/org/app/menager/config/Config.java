package org.app.menager.config;


import javafx.scene.layout.Pane;

import java.nio.file.Path;

public class Config {

    int MapSizeX = 500;
    int MapSizeY = 500;

    int senseRange = 10;
    double AntTurnAngle = 0.001;

    int InitialAntHunger = 100;
    int pheromoneCircleRadius = 2;

    int AntLifetime = 10000;
    int AnthillAntLimit = 10000;
    int MaxStepHeightDelta = 10;
    int AntStepLen = 1;

    double antDecisionFactor = 20;

    double antRange = 10;
    int AntCircleRadius = 5;

    public double getAntTurnAngle() {
        return AntTurnAngle;
    }

    public int getPheromoneCircleRadius() {
        return pheromoneCircleRadius;
    }

    public int getInitialAntHunger() {
        return InitialAntHunger;
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

    public int getMaxStepHeightDelta() {
        return MaxStepHeightDelta;
    }

    public void setMaxStepHeightDelta(int maxStepHeightDelta) {
        MaxStepHeightDelta = maxStepHeightDelta;
    }

    public double getAntDecisionFactor() {
        return antDecisionFactor;
    }

    public void setAntDecisionFactor(double antDecisionFactor) {
        this.antDecisionFactor = antDecisionFactor;
    }

    //    double max_turn_angle;



    private int currentTick = 0;

//    public double getMax_turn_angle() {
//        return max_turn_angle;
//    }
//
//    public void setMax_turn_angle(double max_turn_angle) {
//        this.max_turn_angle = max_turn_angle;
//    }

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