package org.app.agent.ant;

import org.app.agent.Agent;
import org.app.menager.config.Config;
import org.app.agent.pheromone.Pheromone;

import java.util.Random;
import java.util.UUID;
import java.util.Vector;

public class Ant extends Agent {

    private int livedUpdates = 0;
    private double antHunger;

    private double movement_angle;

    private double step_len;
    private Config settings;


    private org.app.map.Map map;

    UUID id_mrowiska;

    public Ant(double antHunger, double antDecisionFactor, UUID id_mrowiska, org.app.map.Map map) {
        super();
        this.antHunger = antHunger;
        this.id_mrowiska = id_mrowiska;
        this.map = map;
    }

    public int getLivedUpdates() {
        return livedUpdates;
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


    public void moveAnt() {

        int[] possibleAngles = {0, 45, 90, 135, 180, 225, 270, 315};

        double dMovement_angle;

        dMovement_angle = settings.getAntDecisionFactor() * movement_angle;

        Random rand = new Random();
        double endAntAngle = rand.nextDouble() * ((movement_angle + dMovement_angle) - (movement_angle - dMovement_angle)) + movement_angle - dMovement_angle;


        double closestAngle = Math.abs(Math.toRadians(possibleAngles[0]));
        double smallestDifference = endAntAngle - closestAngle;

        for (int i = 1; i < possibleAngles.length; i++) {
            double currentDifference = Math.abs(endAntAngle - Math.toRadians(possibleAngles[i]));

            if (currentDifference < smallestDifference) {
                closestAngle = Math.toRadians(possibleAngles[i]);
                smallestDifference = currentDifference;
            }
        }

        int positionX = getLocx();
        int positionY = getLocy();

        if (closestAngle == Math.toRadians(possibleAngles[0])) {
            positionX++;
        } else if (closestAngle == Math.toRadians(possibleAngles[1])) {
            positionX++;
            positionY++;
        } else if (closestAngle == Math.toRadians(possibleAngles[2])) {
            positionY++;
        } else if (closestAngle == Math.toRadians(possibleAngles[3])) {
            positionX--;
            positionY++;
        } else if (closestAngle == Math.toRadians(possibleAngles[4])) {
            positionX--;
        } else if (closestAngle == Math.toRadians(possibleAngles[5])) {
            positionX--;
            positionY--;
        } else if (closestAngle == Math.toRadians(possibleAngles[6])) {
            positionY--;
        } else if (closestAngle == Math.toRadians(possibleAngles[6])) {
            positionX++;
            positionY--;
        }

        int deltaOldLocAndNewLoc;
        int oldLocHeight = map.getHeight(getLocx(), getLocy());
        int newLocHeight = map.getHeight(positionX, positionY);

        deltaOldLocAndNewLoc = Math.abs(oldLocHeight - newLocHeight);

        if (deltaOldLocAndNewLoc <= settings.getMaxStepHeightDelta()) {
            setLocx(positionX);
            setLocy(positionY);
        } else {
            setLocx(getLocx());
            setLocy(getLocy());
        }

        /// a co dalej? jezeli sie nie moge ruszyc? no to nie wiem XD
    }

    public void updateAngle() {

        Vector<Pheromone> pheromones = map.getSurroundingPheromones(getLocx(), getLocy(), settings.getAntRange());

        Pheromone oldestPheromone = pheromones.get(1);

        for (Pheromone p : pheromones) {

            if (p.getCreationTick() < oldestPheromone.getCreationTick()) {
                oldestPheromone = p;
            }
        }

        this.movement_angle = countAngleBeetwenPoints(getLocx(), getLocy(), oldestPheromone.getLocx(), oldestPheromone.getLocy());
    }

    private double countAngleBeetwenPoints(int x1, int y1, int x2, int y2) {
        double dx = x2 - x1;
        double dy = y2 - y1;

        double radianAngle = Math.atan2(dy, dx);

        return radianAngle;
    }

    public void update() {
        livedUpdates++;
    }


    public String toString() {
        return "id: { " + getId() + " }";
    }
}
