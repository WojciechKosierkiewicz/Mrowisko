package org.app.menager.config;


public class Config {
    int AntLifetime;
    int AnthillAntLimit;
    int MaxStepHeightDelta;

    double antRange;

    double max_turn_angle;

    private int currentTick = 0;

    public double getMax_turn_angle() {
        return max_turn_angle;
    }

    public void setMax_turn_angle(double max_turn_angle) {
        this.max_turn_angle = max_turn_angle;
    }

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