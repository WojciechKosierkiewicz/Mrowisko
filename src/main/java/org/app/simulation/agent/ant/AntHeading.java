package org.app.simulation.agent.ant;

import org.app.simulation.agent.pheromone.Pheromone;
import org.app.simulation.agent.pheromone.PheromoneType;
import org.app.simulation.menager.config.Config;

import java.util.Comparator;
import java.util.Vector;

public class AntHeading {

    Config settings;
    double turnangle = 0.001 * Math.PI;
    double currentangle;

    AntHeading(Config settings) {
        this.settings = settings;
        currentangle = Math.random() * (Math.PI * 2);
    }

    public double getHeadingAngle() {
        return currentangle;
    }

    public void setHeadingAngle(double angle) {
        currentangle = angle;
    }

    public void update(double x, double y, Antdirection direction) {
        if (direction == Antdirection.NONE) {
            changecurrentaanglefrompheromones(x, y, direction);
        }
        currentangle = limitpi(currentangle);
        currentangle = currentangle + turnangle;
        turnangle += (Math.random() - 0.5) * settings.getAntTurnAngle();
        if (turnangle > settings.getAntTurnAngleMax()) {
            turnangle = settings.getAntTurnAngleMax();
        }
        if (turnangle < -settings.getAntTurnAngleMax()) {
            turnangle = -settings.getAntTurnAngleMax();
        }
    }

    void changecurrentaanglefrompheromones(double locx, double locy, Antdirection antdirection) {

        if (antdirection == Antdirection.NONE) {
            return;
        }

        if (antdirection == Antdirection.SEARCH) {
            return;
        }

        Vector<Pheromone> pheromones = settings.getMap().getSurroundingPheromones(locx, locy, settings.getAntRange());

        if (pheromones.size() == 0) {
            return;
        }

        if (antdirection == Antdirection.FOOD) {
            pheromones.removeIf(p -> p.getType() != PheromoneType.FOOD);
        }

        if (antdirection == Antdirection.HOME) {
            pheromones.removeIf(p -> p.getType() != PheromoneType.HOME);
        }

        if (pheromones.size() == 0) {
            return;
        }

        pheromones.sort(Comparator.comparingInt(Pheromone::getCreationTick));

        Pheromone youngestPheromone = pheromones.get(pheromones.size() - 1);

        double angle = Math.atan2(youngestPheromone.getLocy() - locy, youngestPheromone.getLocx() - locx);
        angle = limitpi(angle);
        currentangle = angle;
    }

    double limitpi(double angle) {
        if (angle > 2 * Math.PI) {
            angle = angle - 2 * Math.PI;
        }
        if (angle < 0) {
            angle = angle + 2 * Math.PI;
        }
        return angle;
    }

    void bouncexwall() {
        currentangle = Math.PI - currentangle;
    }

    void bounceywall() {
        currentangle = 2 * Math.PI - currentangle;
    }

}
