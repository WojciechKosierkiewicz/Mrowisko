package org.app.menager.config;


public class Config {

    int MapSizeX = 1000;
    int MapSizeY = 900;

    int AntSenseRange = 10;
    int AntLifetime = 1500;
    double AntTurnAngle = 0.001;
    double AntTurnAngleMax = 0.01;


    int AntStepLen = 1;

    double antDecisionFactor = 20;

    double antRange = 10;


    int AnthillAntLimit = 100;
    double AnthillAntSpawnChance = 0.1;


    int AntCircleRadius = 5;
    int AntHillCircleRadius = 10;
    int pheromoneCircleRadius = 2;

    public int getAntHillCircleRadius() {
        return AntHillCircleRadius;
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