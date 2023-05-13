package org.app.ant;

import org.app.agent.Agent;

import java.util.UUID;

public class Ant extends Agent {

    private int livedUpdates = 0;
    private double antHunger;
    private double antDecisionFactor;

    UUID id_mrowiska;

    public Ant() {
        super();
        this.antHunger = 100;
        this.antDecisionFactor = 0.5;
    }

    public Ant(double antHunger, double antDecisionFactor, UUID id_mrowiska) {
        super();
        this.antHunger = antHunger;
        this.antDecisionFactor = antDecisionFactor;
        this.id_mrowiska = id_mrowiska;
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
