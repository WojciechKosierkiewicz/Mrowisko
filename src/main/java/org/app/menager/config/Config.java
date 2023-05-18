package org.app.menager.config;


public class Config {
    int AntLifetime;
    int AnthillAntLimit;
    int MaxStepHeightDelta;

    double antRange;

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