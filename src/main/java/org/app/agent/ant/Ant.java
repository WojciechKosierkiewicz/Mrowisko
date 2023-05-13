package org.app.agent.ant;

import org.app.agent.Agent;

import java.util.UUID;

public class Ant extends Agent {

    private int livedUpdates = 0;
    private double antHunger;
    private double antDecisionFactor;

    private double movement_angle;
    private double turn_angle;
    private double move_len;

    UUID id_mrowiska;

    public Ant() {
        super();
        this.antHunger = 100;
        this.antDecisionFactor = 0.5;
        this.turn_angle = 0;
        this.movement_angle = 0;
        this.move_len = 1;
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

    public void setTurn_angle(double turn_angle) {
        this.turn_angle = turn_angle;
    }

    public void setMovement_angle(double movement_angle) {
        this.movement_angle = movement_angle;
    }

    public double getAntHunger() {
        return antHunger;
    }

    public void setAntHunger(double antHunger) {
        this.antHunger = antHunger;
    }

    public double getAntDecisionFactor() {
        return antDecisionFactor;
    }

    public void setAntDecisionFactor(double antDecisionFactor) {
        this.antDecisionFactor = antDecisionFactor;
    }

    public void moveAnt(double move_len) {
        this.setLocx(this.getLocx() + move_len * Math.cos(movement_angle));
        this.setLocy(this.getLocy() + move_len * Math.sin(movement_angle));
    }

    public void moveAnt() {
        this.setLocx(this.getLocx() + move_len * Math.cos(movement_angle));
        this.setLocy(this.getLocy() + move_len * Math.sin(movement_angle));
    }

    public void update() {
        livedUpdates++;
    }


    public String toString() {
        return "id: { " + getId() + " }";
    }
}
