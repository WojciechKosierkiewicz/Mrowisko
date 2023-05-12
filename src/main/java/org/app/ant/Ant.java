package org.app.ant;

import org.app.agent.Agent;

import java.util.UUID;

public class Ant extends Agent {

    private int livedUpdates = 0;
    private double antHunger;
    private double antDecisionFactor;

    private double antAngle;


    UUID id_mrowiska;

    public Ant() {
        super();
        this.antHunger = 100;
        this.antDecisionFactor = 0.5;
    }

    public Ant(double antHunger, double antDecisionFactor, UUID id_mrowiska, int x, int y) {
        super();
        this.antHunger = antHunger;
        this.antDecisionFactor = antDecisionFactor;
        this.id_mrowiska = id_mrowiska;
        this.setLocx(x);
        this.setLocy(y);
    }


    public int getLivedUpdates() {

        return livedUpdates;
    }

    public double getLocXofAnt() {

        return getLocx();
    }

    public double getLocYOfAnt() {

        return getLocy();
    }

    private void setAntHunger() {

    }

    private void setAntDecisionFactor() {

    }

    private double getAntHunger() {

        return antHunger;
    }

    private double getAntDecisionFactor() {

        return antDecisionFactor;
    }

    public UUID getId_mrowiska() {

        return id_mrowiska;
    }


    private boolean moveTest() {

        return true;
    }

    private double countAntAngle() {


        return antAngle;
    }

    private void moveAnt() {

    }

    public void update() {
        livedUpdates++;
    }


    public String toString() {
        return "id: { " + getId() + " }";
    }
}
