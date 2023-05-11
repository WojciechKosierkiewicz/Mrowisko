package org.app.ant;

import org.app.agent.Agent;

public class Ant extends Agent {

    private int livedUpdates = 0;
    private double antHunger;
    private double antDecisionFactor;

    public Ant() {
        super();
        this.antHunger = 100;
        this.antDecisionFactor = 0.5;
    }

    public Ant(double antHunger, double antDecisionFactor) {
        super();
        this.antHunger = antHunger;
        this.antDecisionFactor = antDecisionFactor;
    }

    public int getLivedUpdates() {
        return livedUpdates;
    }

    private void setAntHunger() {

    }

    private void setAntDecisionFactor() {

    }

    private double getAntHunger() {

        return antHunger;
    }

    private double getAntDecisionFactor(){

        return antDecisionFactor;
    }

    private boolean moveTest() {

        return true;
    }

    private void moveAnt(){

    }

    public void update() {
        livedUpdates++;
    }


    public String toString() {
        return "id: { " + getId() + " }";
    }
}
