package org.app.agent.ant;

import org.app.agent.Agent;
import org.app.menager.config.Config;

import java.util.UUID;

public class Ant extends Agent {

    private int livedUpdates = 0;
    private double antHunger;
    private double antDecisionFactor;

    private double movement_angle;
    private double turn_angle;
    private double step_len;
    private Config settings;


    private org.app.map.Map map;

    UUID id_mrowiska;

    public Ant(double antHunger, double antDecisionFactor, UUID id_mrowiska, org.app.map.Map map) {
        super();
        this.antHunger = antHunger;
        this.antDecisionFactor = antDecisionFactor;
        this.id_mrowiska = id_mrowiska;
        this.map = map;
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

    public void moveAnt() {

    }

    public void updateAngle() {

        java.util.Vector<org.app.agent.pheromone.Pheromone> pheromones = map.getSurroundingPheromones(getLocx(), getLocy(), settings.getAntRange());

        org.app.agent.pheromone.Pheromone oldestPheromone = pheromones.get(1);

        for (org.app.agent.pheromone.Pheromone p : pheromones) {

            if (p.getCreationTick() < oldestPheromone.getCreationTick()) {
                oldestPheromone = p;
            }
        }

        double angle = countAngleBeetwenPoints(getLocx(), getLocy(), oldestPheromone.getLocx(), oldestPheromone.getLocy());
    }

    private double countAngleBeetwenPoints(double x1, double y1, double x2, double y2) {
        // TODO: 18.05.2023
    }

    public void update() {
        livedUpdates++;
    }


    public String toString() {
        return "id: { " + getId() + " }";
    }
}
