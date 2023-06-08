package org.app.simulation.agent.ant;

import org.app.simulation.agent.Agent;
import org.app.simulation.agent.pheromone.Pheromone;
import org.app.simulation.agent.pheromone.PheromoneType;
import org.app.simulation.menager.config.Config;

import java.util.Comparator;
import java.util.Vector;

public class AntHeading {

    Config settings;
    double turnangle = 0.001 * Math.PI;
    double currentangle;
    Ant owner;

    AntHeading(Config settings, Ant owner) {
        this.settings = settings;
        this.owner = owner;
        currentangle = Math.random() * (Math.PI * 2);
    }

    public double getHeadingAngle() {
        return currentangle;
    }

    public void setHeadingAngle(double angle) {
        currentangle = angle;
    }

    public void update() {
        changecurrentaanglefrompheromones();

        currentangle = currentangle + turnangle;

        turnangle += (Math.random() - 0.5) * settings.getAntTurnAngleChange();
    }

    void removePheromonesNotInFov(Vector<Pheromone> pheromones) {
        pheromones.removeIf(p -> {
            double angle = Math.atan2(p.getLocy() - owner.getLocy(), p.getLocx() - owner.getLocx());
            return Math.abs(angle - currentangle) > settings.getAntFov();
        });
    }

    double getmeanAnglefromPheromones(Vector<Pheromone> pheromones) {

        double meanAngle = 0;

        for (Pheromone p : pheromones) {
            meanAngle += Math.atan2(p.getLocy() - owner.getLocy(), p.getLocx() - owner.getLocx());
        }

        return meanAngle / pheromones.size();
    }

    void changecurrentaanglefrompheromones() {

        Vector<Pheromone> pheromones = settings.getMap().getSurroundingPheromones(owner.getLocx(), owner.getLocx(), settings.getSenseRange() * 100);

        //exit if there are no pheromones to process
        if (pheromones.size() == 0) {
            return;
        }

        //remove pheromones not currently looked for by the ant
        switch (owner.getDirection()) {
            case FOOD -> {
                pheromones.removeIf(p -> p.getType() != PheromoneType.FOOD);

                removePheromonesNotInFov(pheromones);

                if (pheromones.size() == 0)
                    return;

                pheromones.sort(Comparator.comparingInt(Pheromone::getCreationTick));

                currentangle = findAngleToAgent(pheromones.lastElement());

            }
            case HOME -> {
                pheromones.removeIf(p -> p.getType() != PheromoneType.HOME);
                pheromones.removeIf(p -> p.getCreator() != owner);

                if (pheromones.size() == 0) {
                    return;
                }

                //sorting pheromones by age
                pheromones.sort(Comparator.comparingInt(Pheromone::getCreationTick));

                if (pheromones.size() == 0) {
                    return;
                }

                currentangle = findAngleToAgent(pheromones.lastElement());
            }

            default -> {
            }
        }

        //exit if ant sees nothing

        //get the mean angle of all pheromones of intereset and head that way
        currentangle = getmeanAnglefromPheromones(pheromones);
    }

    double findAngleToAgent(Agent agent) {
        return owner.countAngleBeetwenPoints(agent.getLocx(), agent.getLocy(), owner.getLocx(), owner.getLocy());
    }

    void reverseDirection() {
        currentangle += Math.PI;
    }

    void bouncexwall() {
        currentangle = Math.PI - currentangle;
    }

    void bounceywall() {
        currentangle = 2 * Math.PI - currentangle;
    }

}
